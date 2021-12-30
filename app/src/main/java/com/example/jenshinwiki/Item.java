package com.example.jenshinwiki;

//Ini merupakan object Item yang memiliki variable : id, item_name, item_description,
//item_image. Yang dilengkapi dengan getter dan setter untuk masing - masing variblenya
public class Item {
    String id, item_name, item_description, item_image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }
}