package entity;

public class BookCategoryEntity implements SuperEntity {


    private String id;
    private String CategoryName;

    public BookCategoryEntity() {
    }

    

    public BookCategoryEntity(String id, String categoryName) {
        this.id = id;
        this. CategoryName = categoryName;
    }
 


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
            this.CategoryName = categoryName;
    }

    @Override
    public String toString() {
        return "BookCategoryEntity [id=" + id + ", CategoryName=" + CategoryName + "]";
    }


}