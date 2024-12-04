package org.example.lootlogkopalniany.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LootMessage {

    @JsonProperty("user")
    private String userId;

    @JsonProperty("item")
    private Item item;

    public String getUserId() {
        return userId;
    }

    public Item getItem() {
        return item;
    }

    public static class Item {
        @JsonProperty("hid")
        private String hid;

        @JsonProperty("name")
        private String name;

        @JsonProperty("stat")
        private String stat;

        public String getHid() {
            return hid;
        }

        public String getName() {
            return name;
        }

        public String getStat() {
            return stat;
        }
    }
}
