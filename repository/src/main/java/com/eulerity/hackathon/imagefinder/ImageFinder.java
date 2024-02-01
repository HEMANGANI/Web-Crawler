package com.eulerity.hackathon.imagefinder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(
    name = "ImageFinder",
    urlPatterns = {"/main"}
)

public class ImageFinder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected static final Gson GSON = new GsonBuilder().create();
    private static final int THREAD_POOL_SIZE = 10; // Consider making this configurable
    private static final long REQUEST_DELAY_MS = 1000; // 1 second delay

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String url = req.getParameter("url");
        System.out.println("Got request with URL: " + url);

        List<String> imageUrls = new CopyOnWriteArrayList<>();
        ConcurrentHashMap<String, Boolean> visitedUrls = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try {
            crawlPage(url, url, imageUrls, visitedUrls, executorService);
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            if (!executorService.isShutdown()) {
                executorService.shutdownNow();
            }
        }

        resp.getWriter().print(GSON.toJson(imageUrls));
    }

    private void crawlPage(String baseUrl, String currentUrl, List<String> imageUrls, ConcurrentHashMap<String, Boolean> visitedUrls, ExecutorService executorService) {
        if (visitedUrls.putIfAbsent(currentUrl, true) != null) {
            return; // already been visited
        }

        executorService.submit(() -> {
            try {
                Thread.sleep(REQUEST_DELAY_MS); // polite crawling
                Document doc = Jsoup.connect(currentUrl)
                                    .userAgent("Mozilla/5.0 (compatible; ImageFinderBot/1.0; +http://yourwebsite.com/bot)")
                                    .get();
                Elements images = doc.select("img");
                for (Element img : images) {
                    imageUrls.add(img.absUrl("src"));
                }

                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String linkUrl = link.absUrl("href");
                    if (linkUrl.startsWith(baseUrl) && !visitedUrls.containsKey(linkUrl)) {
                        executorService.submit(() -> crawlPage(baseUrl, linkUrl, imageUrls, visitedUrls, executorService));
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
    }
}
