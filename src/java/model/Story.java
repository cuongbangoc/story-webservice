package model;

import java.io.Serializable;

public class Story implements Serializable{
	private int id;
	private int idCategory;
	private String name;
	private String content;
        private String language;
	private String note;

    public Story(int id, int idCategory, String name, String content, String language, String note) {
        this.id = id;
        this.idCategory = idCategory;
        this.name = name;
        this.content = content;
        this.language = language;
        this.note = note;
    }

    public Story() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
	
	
	
}
