package com.example.jenshinwiki;

//Ini merupakan object Monster yang memiliki variable : id, monster_name, monster_description,
//monster_image. Yang dilengkapi dengan getter dan setter untuk masing - masing variblenya
public class Monster {
    String id, monster_name, monster_description, monster_image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonster_name() {
        return monster_name;
    }

    public void setMonster_name(String monster_name) {
        this.monster_name = monster_name;
    }

    public String getMonster_description() {
        return monster_description;
    }

    public void setMonster_description(String monster_description) {
        this.monster_description = monster_description;
    }

    public String getMonster_image() {
        return monster_image;
    }

    public void setMonster_image(String monster_image) {
        this.monster_image = monster_image;
    }
}