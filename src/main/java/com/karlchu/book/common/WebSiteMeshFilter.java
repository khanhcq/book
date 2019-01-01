package com.karlchu.book.common;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * Created by KhanhChu on 12/27/2018.
 */
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/decorators/default_pub.jsp")
                .addDecoratorPath("/admin/*", "/default_ace.jsp")
                .addDecoratorPath("/login*", "/login_ace.jsp")
                .addDecoratorPath("/forgotpwd*", "/login_ace.jsp")
                .addExcludedPath("/api/*")
                .addExcludedPath("/mobile/*")
                .addExcludedPath("/ajax/*")
                .addExcludedPath("/scripts/*")
                .addExcludedPath("/ckeditor442/*")
                .addExcludedPath("/api/*")
                .addExcludedPath("/viewer");
    }
}
