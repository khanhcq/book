package com.karlchu.crawler.truyen_yy;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "sitemap")
public class SiteMap {
    private String loc;
    private String lastmod;
    private String changefreq;
    private String priority;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLastmod() {
        return lastmod;
    }

    public void setLastmod(String lastmod) {
        this.lastmod = lastmod;
    }

    public String getChangefreq() {
        return changefreq;
    }

    public void setChangefreq(String changefreq) {
        this.changefreq = changefreq;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
