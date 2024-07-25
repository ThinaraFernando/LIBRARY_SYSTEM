package dto;

public class BookCategoryDto {

    private String id;
    private String CategoryName;


    public BookCategoryDto() {
    }


    public BookCategoryDto(String id, String categoryName) {
        this.id = id;
        this.CategoryName = categoryName;
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
        return "BookCategoryDto [id=" + id + ", CategoryName=" + CategoryName + "]";
    }

   



}
