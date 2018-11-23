import static org.junit.Assert.assertEquals;

import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import org.junit.Before;
import org.junit.Test;


public class WarehouseManagementTest {

    private SoldItem item1;

    @Before
    public void setUp() {
        item1 = new SoldItem();
    }

    /*
    @Test
  public void testRowSumWithZeroQuantity() {
    BillRow r = new BillRow(item1, 0);

    assertEquals(0, r.getRowPrice(), 0.0001);
  }
     */
}
