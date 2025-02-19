package entity;

public class BookCategoryEntity {

    private int categoryId;
    private String categoryName;

    public BookCategoryEntity() {}

    public BookCategoryEntity(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "BookCategoryEntity [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
    }
}

