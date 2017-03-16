package com.example.austi.boojjsonandroidapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by austi on 3/12/2017.
 */

public class Response {

    public List<RealtorsBean> realtors;

//    public static List<RealtorsBean> staticRealtors;

    public static RealtorsBean selectedRealtor;

    public List<RealtorsBean> getRealtors() {
//        for (int i = 1; i <= realtors.size(); i++) {
//            ITEM_MAP.put(item.id, item);
//        }


        return realtors;
    }

    public void setRealtors(List<RealtorsBean> realtors) {
        this.realtors = realtors;
//        this.staticRealtors = realtors;
    }

    public static final Map<String, RealtorsBean> ITEM_MAP = new HashMap<String, RealtorsBean>();



    public static class RealtorsBean {
        /**
         * first_name : Baumgartner-Barrett
         * last_name :
         * id : 443540
         * rebrand : baum
         * office : Kentwood Cherry Creek
         * is_team : false
         * phone_number : 303-919-4545
         * photo : https://dnn6wxct3pddm.cloudfront.net/pics/realtor/443540/76310/
         */

        public String first_name;
        public String last_name;
        public String id;
        public String rebrand;
        public String office;
        public boolean is_team;
        public String phone_number;
        public String photo;


        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRebrand() {
            return rebrand;
        }

        public void setRebrand(String rebrand) {
            this.rebrand = rebrand;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public boolean isIs_team() {
            return is_team;
        }

        public void setIs_team(boolean is_team) {
            this.is_team = is_team;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
