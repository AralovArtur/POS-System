package ee.ut.math.tvt.salessystem.dataobjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryItem {

    private Long id;

    private LocalDateTime date;

    private List<SoldItem> items;


    public HistoryItem() {
    }

    public HistoryItem(LocalDateTime date){
        this.date = date;
        this.items = new ArrayList<>();
    }


    public void addItem(SoldItem item){
        items.add(item);
    }

    public void removeItem(SoldItem item){
        items.remove(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<SoldItem> getItems() {
        return items;
    }

    public void setItems(List<SoldItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "HistoryItem{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
