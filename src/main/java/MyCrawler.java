import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.Set;
import java.util.regex.Pattern;

/**
*@Auther z
*@Date 2018-09-11 16:15
*@Describe
*/
public class MyCrawler extends WebCrawler {
    //自定义过滤规则
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();// 得到小写的url
        return !FILTERS.matcher(href).matches() // 正则匹配，过滤掉我们不需要的后缀文件
                && href.startsWith("https://oureverydaylife.com/");// 只接受以“http://www.ics.uci.edu/”开头的url
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();// 获取url
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {// 判断是否是html数据
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();//// 强制类型转换，获取html数据对象
            String text = htmlParseData.getText();//获取页面纯文本（无html标签）
            String html = htmlParseData.getHtml();//获取页面Html
            Set<WebURL> links = htmlParseData.getOutgoingUrls();// 获取页面输出链接

            /**Jsoup解析*/
            Document document=Jsoup.parse(html);
            /**选择p标签*/
            Elements h=document.select("h1");
            Elements author=document.select("cite");
            Elements element=document.select("p");
            /**抽取p标签里的text，并保留换行换行，element.text()方法没保留换行*/
            String content=Jsoup.clean(element.toString(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

//            System.out.println("纯文本长度: " + text.length());
//            System.out.println("html长度: " + html.length());
//            System.out.println("链接个数 " + links.size());
//            System.out.println(h.text());
//            System.out.println(content);
            sava.write(h.text());
            sava.write(author.text());
            sava.write(content);
        }
    }


}
