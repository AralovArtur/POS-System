package ee.ut.math.tvt.salessystem.dataobjects;


import javax.persistence.*;

/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history.
 */
@Entity
@Table
public class SoldItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private StockItem stockItem;

    @ManyToOne
    private HistoryItem historyId;

    @Column
    private String name;

    @Column
    private Integer quantity;

    @Column
    private double price;

    public SoldItem() {
    }

    public SoldItem(StockItem stockItem, int quantity) {
        this.id = stockItem.getId();
        this.stockItem = stockItem;
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
        this.quantity = quantity;
    }

    public SoldItem(StockItem stockItem, HistoryItem historyId, String name, Integer quantity, double price) {
        this.stockItem = stockItem;
        this.historyId = historyId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public HistoryItem getHistoryId() {
        return historyId;
    }

    public void setHistoryId(HistoryItem historyId) {
        this.historyId = historyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return price * ((double) quantity);
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    @Override
    public String toString() {
        return String.format("SoldItem{id=%d, name='%s'}", id, name);
    }
}
