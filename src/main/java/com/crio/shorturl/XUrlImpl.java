package com.crio.shorturl;
import java.util.*;
class XUrlImpl implements XUrl{
    public HashMap<String,String>  longToShortMap;
    public HashMap<String,String>  shortToLongMap;
    public HashMap<String,Integer>  hitCountMap;
    private final String alphaString ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz"; 
    public XUrlImpl() {
        longToShortMap= new HashMap<>();
        shortToLongMap= new HashMap<>(); 
        hitCountMap= new HashMap<>();   
    }

    private String generateUniqueString(){
        StringBuilder uniqueString=new StringBuilder();
        for(int i=0;i<9;i++){
                uniqueString.append(alphaString.charAt((int)(alphaString.length()*Math.random())));
        }
        return uniqueString.toString();
    }
    @Override
    public String registerNewUrl(String longUrl) {
        // System.out.println(shortToLongMap);
        if (longToShortMap.containsKey(longUrl)) return longToShortMap.get(longUrl);
        String uString=generateUniqueString();
        String shortUrl="http://short.url/"+uString;
        longToShortMap.put(longUrl,shortUrl);
        shortToLongMap.put(shortUrl,longUrl);
        return shortUrl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if (!longToShortMap.containsKey(longUrl) && !shortToLongMap.containsKey(shortUrl)){
            longToShortMap.put(longUrl,shortUrl);
            shortToLongMap.put(shortUrl,longUrl);
            return shortUrl;
        }
        return null;
    }

    @Override
    public String getUrl(String shortUrl) {
        // System.out.println(shortToLongMap);
        if (shortToLongMap.containsKey(shortUrl)){
            String lUrl=shortToLongMap.get(shortUrl);
            // System.out.println("lUrl : "+lUrl);
            hitCountMap.put(lUrl,hitCountMap.getOrDefault(lUrl, 0)+1);
            return lUrl;
        } 
        return null;

    }

    @Override
    public Integer getHitCount(String longUrl) {
        // TODO Auto-generated method stub
        if (hitCountMap.containsKey(longUrl)) return hitCountMap.get(longUrl);
        return 0;
    }

    @Override
    public String delete(String longUrl) {
        // TODO Auto-generated method stub
        if (longToShortMap.containsKey(longUrl)){ 
            shortToLongMap.remove(longToShortMap.get(longUrl));
            longToShortMap.remove(longUrl);
            return null;
        }

        return null;
    }
    

}