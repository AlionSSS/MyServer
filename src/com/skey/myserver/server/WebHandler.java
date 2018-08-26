package com.skey.myserver.server;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet SAX解析Handler
 *
 * @author ALion
 * @version 2018/8/5 10:22
 */
public class WebHandler extends DefaultHandler {

    private List<Entity> entityList;
    private List<Mapping> mappingList;
    private Entity entity;
    private Mapping mapping;
    private String tag;
    private boolean isMap;

    public WebHandler() {
        super();
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("解析XML文档->开始");
        entityList = new ArrayList<>();
        mappingList = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("解析XML文档->结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null) {
            tag = qName;
            if ("servlet".equals(tag)) {
                isMap = false;
                entity = new Entity();
            } else if ("servlet-mapping".equals(tag)) {
                isMap = true;
                mapping = new Mapping();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName != null) {
            if ("servlet".equals(qName)) {
                entityList.add(entity);
            } else if ("servlet-mapping".equals(qName)) {
                mappingList.add(mapping);
            }
        }
        tag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (tag != null) {
            String str = new String(ch, start, length);
            if (!isMap) {
                if ("servlet-name".equals(tag)) {
                    entity.setName(str);
                } else if ("servlet-class".equals(tag)) {
                    entity.setClazz(str);
                }
            } else {
                if ("servlet-name".equals(tag)) {
                    mapping.setName(str);
                } else if ("url-pattern".equals(tag)) {
                    mapping.getUrlPattern().add(str);
                }
            }
        }
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public List<Mapping> getMappingList() {
        return mappingList;
    }
}
