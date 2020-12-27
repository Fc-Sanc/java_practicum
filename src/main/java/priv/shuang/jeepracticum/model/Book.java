package priv.shuang.jeepracticum.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Book {
    private String     isbn;
    private String     name;
    private String     author;
    private String     press;
    private Date       publicationDate;
    private BigDecimal unitPrice;
    private int        sales;
    private String     brief;
    private String     cover;
    private transient Type       type;

    @Id
    @Column(name = "ISBN", nullable = false, length = 20)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 50)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "press", nullable = true, length = 50)
    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    @Basic
    @Column(name = "publication_date", nullable = true)
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Basic
    @Column(name = "unit_price", nullable = true, precision = 2)
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "sales", nullable = false)
    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Basic
    @Column(name = "brief", nullable = true, length = -1)
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Basic
    @Column(name = "cover", nullable = false, length = 255)
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return sales == book.sales &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(name, book.name) &&
                Objects.equals(author, book.author) &&
                Objects.equals(press, book.press) &&
                Objects.equals(publicationDate, book.publicationDate) &&
                Objects.equals(unitPrice, book.unitPrice) &&
                Objects.equals(brief, book.brief) &&
                Objects.equals(cover, book.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, name, author, press, publicationDate, unitPrice, sales, brief, cover);
    }

}
