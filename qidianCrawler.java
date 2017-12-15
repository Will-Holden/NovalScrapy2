package cn.edu.hfut.dmic.NovalScrapy;

import cn.edu.hfut.dmic.webcollector.crawldb.DBManager;
import cn.edu.hfut.dmic.webcollector.fetcher.Executor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BerkeleyDBManager;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
//import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

/**
 * created by lxl 2017.12.15
 * used to crawl noval from web qidian
 */
public class qidianCrawler extends BreadthCrawler{
    int count = 0;
    public qidianCrawler(String crawlPath, boolean autoParse){
        super(crawlPath, autoParse);

//        addSeed("https://www.qidian.com/book/strongrec");
//        addRegex("//book.qidian.com/info/[0-9]+");
        for (int i =1; i< 8; i++){
            addSeed("https://www.qidian.com/book/strongrec?page=" + i);
        }


        getConf().setExecuteInterval(1000);
        setThreads(30);
    }
    /*

     */
    @Override
    public void visit(Page page, CrawlDatums next) {
        if(page.matchUrl("https://www.qidian.com/book/.*")){
            Elements books = page.select("li>strong>a[class=name]");
            for(Element element: books){
                String nextUrl = element.attr("href");
                System.out.print(nextUrl);
                System.out.println(element.text());
                count ++;
                System.out.println(count);


            }

        }
    }

    public static void main(String[] args) throws Exception{
        qidianCrawler crawler = new qidianCrawler("crawl",true);
//        DBManager manager = new BerkeleyDBManager("crawl");
//        Executor executor = new Executor() {
//            @Override
//            public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
//
//                HtmlUnitDriver driver = new HtmlUnitDriver();
//                driver.setJavascriptEnabled(true);
//
//                driver.get(datum.url());
//
//                List<WebElement> elementList = driver.findElementsByCssSelector("h3.vrTitle a");
//                for(WebElement element:elementList){
//                    System.out.println("title:"+element.getText());
//                }
//            }
//        };
//        crawler.setDBManager(manager);
//        crawler.setExecutor(executor);
        crawler.start(2);
    }
}
