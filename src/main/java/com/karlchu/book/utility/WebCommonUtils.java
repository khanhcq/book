package com.karlchu.book.utility;

import com.karlchu.book.command.AbstractCommand;
import org.apache.commons.lang.StringUtils;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: khanhcq
 * Date: 6/3/14
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebCommonUtils {
    public static Boolean checkDeletable(String role){
        Boolean deletable = Boolean.FALSE;
        if(role.equals(Constants.ADMIN_ROLE)){
            deletable = Boolean.TRUE;
        }
        return  deletable;
    }

    public static Double productWidth(String size){
        Double w;
        try{
            String[] sizeArr = StringUtils.split(size, "x");
            w = Double.valueOf(sizeArr[sizeArr.length-1]) / 1000;
        }catch (Exception e){
            w = 0d;
        }
        return w;
    }

    public static Double productThick(String size){
        Double w;
        try{
            String[] sizeArr = StringUtils.split(size, "x");
            w = Double.valueOf(sizeArr[0]);
        }catch (Exception e){
            w = 0d;
        }
        return w;
    }

    public static Double balanceArrangeFee(Double arrangeCost, Double thick){
        Double result;
        try{
            if(thick <= 0.25){
                result = arrangeCost - arrangeCost*0.06;
            }else if(thick > 0.25 && thick <= 0.3){
                result = arrangeCost - arrangeCost*0.03;
            }else if(thick >= 0.31 && thick <= 0.4){
                result = arrangeCost + arrangeCost*0.03;
            }else if(thick >= 0.41 && thick <= 0.5){
                result = arrangeCost + arrangeCost*0.06;
            }else if(thick >= 0.51 && thick <= 0.6){
                result = arrangeCost + arrangeCost*0.09;
            }else{
                result = arrangeCost + arrangeCost*0.12;
            }
        }catch (Exception e){
            result = 0d;
        }
        return result;
    }

    public static String productCodeWhenPrint(String code){
        String result = code;
        if(StringUtils.isNotBlank(code) && code.lastIndexOf("-") > 0){
            result = StringUtils.substring(code,0, code.lastIndexOf("-"));
        }
        return result;
    }

    public static Boolean checkImage(String fileName){
        Boolean result = Boolean.FALSE;
        fileName = fileName.toLowerCase();
        if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||fileName.endsWith(".png") || fileName.endsWith(".bmp") || fileName.endsWith(".gif")){
            result = Boolean.TRUE;
        }
        return  result;
    }

    public static String getFileName(String path){
        if(path.indexOf("/") > -1){
            String[] paths =  StringUtils.split(path,"/");
            path = paths[paths.length - 1];
        }
        if(path.length() > 10){
            String[] temp = StringUtils.split(path,".");
            path = temp[0].substring(0,7) + "..." + temp[temp.length - 1];
        }
        return path;
    }

    public static String pagination(AbstractCommand command, String url){
        int currentPage = command.getPage();
        int totalPage = command.getTotalPages();
        StringBuilder sb = new StringBuilder();

        if(totalPage > 1){
            sb.append("<ul class=\"pagination\">");
            if(currentPage - 3 > 1){
                sb.append("<li><a href=\"").append(url).append("page=1")
                        .append("\"><i class=\"ace-icon fa fa-angle-left\"></i></a></li>");
            }

            for(int i = currentPage - 3; i < currentPage + 4; i++){
                if(i > 0 && i <= totalPage){
                    if(i != currentPage) {
                        sb.append("<li><a href=\"").append(url).append("page=").append(i)
                                .append("\">").append(i).append("</a></li>");
                    } else {
                        sb.append("<li><span class=\"active\">").append(i).append("</span></li>");
                    }

                }
            }

            if(currentPage + 3 < totalPage){
                sb.append("<li><a href=\"").append(url).append("page=").append(totalPage)
                        .append("\"><i class=\"ace-icon fa fa-angle-right\"></i></a></li>");
            }
            if(totalPage > 5){
                sb.append("<li><a href=\"").append(url).append("all=true")
                        .append("\"> All </a></li>");
            }

            sb.append("</ul>");
        }

        return sb.toString();
    }

    public static String seoURL(String input) {
        if (input == null) return  "";
        String result = Normalizer.normalize(input, Normalizer.Form.NFD);
        result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        result = result.replace('đ', 'd');
        result = result.replace('Đ', 'D');
        result = result.replaceAll("[^a-z A-Z0-9-]", "");
        result = result.replaceAll(" ", "-");
        result = result.replaceAll("--", "-");
        return result.toLowerCase();
    }

    public static String normalizeFilename(String input) {
        String result = Normalizer.normalize(input, Normalizer.Form.NFD);
        result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        result = result.replace('đ', 'd');
        result = result.replace('Đ', 'D');
        result = result.replaceAll("[^a-z A-Z0-9-\\.]", "");
        result = result.replaceAll(" ", "-");
        return result;
    }

    public static String normalizeTitle(String input) {
        String result = Normalizer.normalize(input, Normalizer.Form.NFD);
        result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        result = result.replace('đ', 'd');
        result = result.replace('Đ', 'D');
        result = result.replaceAll("[^a-z A-Z0-9-_\\.]", "");
        result = result.replaceAll(" ", "-");
        return result;
    }


    public static String getFirstLetter(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern p = Pattern.compile("\\b[A-Za-z]|[\\d]");
        Matcher m = p.matcher(s);

        while (m.find()) {
            stringBuilder.append(m.group());
        }
        return stringBuilder.toString().toLowerCase();
    }
}
