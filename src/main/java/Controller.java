import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
*@Auther z
*@Date 2018-09-11 16:12
*@Describe
*/
public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";//文件存储位置
        int numberOfCrawlers = 1;//线程数量

        CrawlConfig config = new CrawlConfig();// 定义爬虫配置
        config.setCrawlStorageFolder(crawlStorageFolder);// 设置爬虫文件存储位置

        /*
         * 实例化爬虫控制器。
         */
        PageFetcher pageFetcher = new PageFetcher(config);// 实例化页面获取器
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();// 实例化爬虫机器人配置
        // 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件
        // 规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        // 实例化爬虫控制器
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * 对于每次抓取，您需要添加一些种子网址。 这些是抓取的第一个URL，然后抓取工具开始跟随这些页面中的链接
         */

        /**从配置文件随机读取*/
        InputStream insss =Object.class.getResourceAsStream("/url.properties");
        Properties pss = new Properties();
        pss.load(insss);
        Random random=new Random();
        int i=random.nextInt(pss.size());

        /**addSeed随机读取的value*/
//        controller.addSeed(pss.getProperty(String.valueOf(i)));
//        controller.addSeed("https://www.parents.com/fun/games/");
        controller.addSeed("https://oureverydaylife.com/party-games-for-kids-parents-12369270.html");

        /**
         * 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }

}
