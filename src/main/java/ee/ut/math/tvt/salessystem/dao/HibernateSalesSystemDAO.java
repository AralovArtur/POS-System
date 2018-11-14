package ee.ut.math.tvt.salessystem.dao;

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
    }

    // TODO implement missing methods

    public void close() {
        em.close();
        emf.close();
    }

    @Override
    public List<StockItem> findStockItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StockItem> query = cb.createQuery(StockItem.class);
        Root<StockItem> stockItemRoot = query.from(StockItem.class);
        query.select(stockItemRoot.as(StockItem.class));
        //System.out.println(em.createQuery(query).getResultList());
        return em.createQuery(query).getResultList();
    }

    @Override
    public StockItem findStockItem(long id) {
        return em.find(StockItem.class, id);
    }

    @Override
    public void saveStockItem(StockItem stockItem) {
        em.persist(stockItem);
        em.flush(); // Writes changes to DB
    }

    @Override
    public void saveSoldItem(SoldItem soldItem) {
        em.persist(soldItem);
        em.flush();
    }

    @Override
    public List<HistoryItem> findHistoryItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<HistoryItem> query = cb.createQuery(HistoryItem.class);
        Root<StockItem> stockItemRoot = query.from(StockItem.class);
        query.select(stockItemRoot.as(HistoryItem.class));
        //System.out.println(em.createQuery(query).getResultList());
        return em.createQuery(query).getResultList();
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
    public void saveHistoryItem(HistoryItem historyItem) {
        em.persist(historyItem);
        em.flush();
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
        em.remove(stockItem);
    }
}
