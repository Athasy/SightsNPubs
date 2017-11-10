package com.bme.athasy.sightsnpubs;

import com.orm.SugarRecord;

/**
 * Created by Athasy on 2017. 11. 05..
 */

public class SightItem extends SugarRecord {

    /* Enumeracio a listaelemek kategoriajahoz */
    public enum Category{
        PUB, SIGHT;

        /* Kategoria kivalasztasahoz */
        public static Category getByOrdinal(int ord){
            Category ret = null;
            for(Category cat : Category.values()){
                if(cat.ordinal() == ord){
                    ret = cat;
                    break;
                }
            }
            return ret;
        }
    }

    public String name;
    public String description;
    public String type;
    public Category category;
    public float verticalCoord;
    public float horizontalCoord;
}
