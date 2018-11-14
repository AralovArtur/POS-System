package ee.ut.math.tvt.salessystem.dao;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import ee.ut.math.tvt.salessystem.dataobjects.HistoryItem;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class HibernateSalesSystemDAO implements SalesSystemDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public HibernateSalesSystemDAO() {
            // if you get ConnectException/JDBCConnectionException then you
            // probably forgot to start the database before starting the application
            emf = Persistence.createEntityManagerFactory("pos");
            em = emf.createEntityManager();
            em.getTransaction().begin();



            StockItem si = new StockItem("Lays chips", 11.0, "Potato chips", 5);

            em.persist(si);
            em.flush();

            em.getTransaction().commit();

    }

    // TODO implement missing methods

    public void close() {
        em.close();
        emf.close();
    }

    @Override
    public List<StockItem> findStockItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StockItem> cq = cb.createQuery(StockItem.class);
        Root<StockItem> stockItemRoot = cq.from(StockItem.class);
        cq.select(stockItemRoot.as(StockItem.class));
        System.out.println(em.createQuery(cq).getResultList());
        return em.createQuery(cq).getResultList();
    }

    @Override
    public StockItem findStockItem(long id) {
        return null;
    }

    @Override
    public void saveStockItem(StockItem stockItem) {

    }

    @Override
    public void saveSoldItem(SoldItem item) {

    }

    @Override
    public List<HistoryItem> findHistoryItems() {
        return null;
    }

    @Override
    public List<HistoryItem> findHistoryItemsBetween(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<HistoryItem> find10LastHistoryItems() {
        return null;
    }

    @Override
    public void saveHistoryItem(HistoryItem item) {

    }

    @Override
    public void beginTransaction() {
        em.getTransaction().begin();
    }

    @Override
    public void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Override
    public void commitTransaction() {
        em.getTransaction().commit();
    }

    @Override
    public void removeStockItem(StockItem stockItem) {

    }
}
