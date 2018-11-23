import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.exception.SalesSystemException;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {
    private static InMemorySalesSystemDAO dao;
    private static ShoppingCart shoppingCart;

    @BeforeClass
    public static void beforeClass() throws SalesSystemException {
        dao = new InMemorySalesSystemDAO();
        dao.saveStockItem(new StockItem(1L,"Beer", "The best beer",5.0, 50));
        shoppingCart = new ShoppingCart(dao);
    }

    @Test
    public void testAddingExistingItem() {
        StockItem stockItem = dao.findStockItem(1L);
        SoldItem soldItem1 = new SoldItem(stockItem, 5);
        soldItem1.setId(stockItem.getId());
        SoldItem soldItem2 = new SoldItem(stockItem, 5);
        soldItem2.setId(stockItem.getId());
        shoppingCart.addItem(soldItem1);
        shoppingCart.addItem(soldItem2);
        List<SoldItem> soldItems = shoppingCart.getAll();
        int kontroll = 0;
        for (SoldItem item: soldItems) {
            if (item.getName() == soldItem1.getName())
                kontroll = item.getQuantity();
        }
        assertEquals(kontroll, 10);
    }

    @Test
    public void testAddingNewItem() {
        StockItem stockItem = dao.findStockItem(1L);
        SoldItem soldItem = new SoldItem(stockItem, 5);
        soldItem.setId(stockItem.getId());
        shoppingCart.addItem(soldItem);
        boolean isAdded = shoppingCart.getAll().contains(soldItem);
        assertEquals(isAdded, true);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithNegativeQuantity() {
        StockItem stockItem = dao.findStockItem(1L);
        SoldItem soldItem = new SoldItem(stockItem, -5);
        shoppingCart.addItem(soldItem);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithQuantityTooLarge() {
        StockItem stockItem = dao.findStockItem(1L);
        SoldItem soldItem = new SoldItem(stockItem, 55);
        soldItem.setId(stockItem.getId());
        shoppingCart.addItem(soldItem);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithQuantitySumTooLarge() {
        StockItem stockItem = dao.findStockItem(1L);
        SoldItem soldItem1 = new SoldItem(stockItem, 5);
        soldItem1.setId(stockItem.getId());
        SoldItem soldItem2 = new SoldItem(stockItem, 50);
        soldItem2.setId(stockItem.getId());
        shoppingCart.addItem(soldItem1);
        shoppingCart.addItem(soldItem2);
    }
}
