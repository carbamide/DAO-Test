package com.daotest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator
{
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.decisionpt.daotest");
        Entity item = schema.addEntity("Item");
        item.addIdProperty();
        item.addStringProperty("content");
        item.addDateProperty("createdAt");

        new DaoGenerator().generateAll(schema, "../../../");
    }
}
