//========//========//========//========//========
package Application.Screens.AssetScreen;

//========//========//========//========//========
import Application.Objects.DBConnection;
import java.util.TreeSet;
import java.util.Set;
import java.sql.*;

//========//========//========//========//========//========//========//========//========//========
/**
 * Class for retrieving data from the XML file holding the asset list.
 */
public class JDBCAssetDataSource implements AssetDataSource
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static final String ASSET_ID = "assetID";
    public static final String ASSET_NAME = "assetName";
    public static final String ASSET_DESCRIPTION = "assetDescription";
    public static final String ASSET_CATEGORY = "assetCategory";
    public static final String ASSET_ACTIVE = "assetActive";
    public static final String ASSET_COLOUR = "assetColour";
    private static final Integer VARCHAR_LIMIT = 60;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS assets ("
                    + ASSET_ID + " INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + ASSET_NAME + " VARCHAR(" + VARCHAR_LIMIT + ") NOT NULL UNIQUE,"
                    + ASSET_DESCRIPTION + " VARCHAR(" + VARCHAR_LIMIT + ") NOT NULL,"
                    + ASSET_CATEGORY + " VARCHAR(" + VARCHAR_LIMIT + ") NOT NULL,"
                    + ASSET_ACTIVE + " TINYINT NOT NULL,"
                    + ASSET_COLOUR + " VARCHAR(" + VARCHAR_LIMIT + ") NOT NULL);";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private static final String INSERT_ASSET =
            "INSERT INTO assets ("
            + ASSET_ID + ", "
            + ASSET_NAME + ", "
            + ASSET_DESCRIPTION + ", "
            + ASSET_CATEGORY + ", "
            + ASSET_ACTIVE + ", "
            + ASSET_COLOUR
            + ") VALUES (?, ?, ?, ?, ?, ?);";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private static final String MODIFY_ASSET =
            "UPDATE assets SET "
                    + ASSET_NAME + " = ?, "
                    + ASSET_DESCRIPTION + " = ?, "
                    + ASSET_CATEGORY + " = ?, "
                    + ASSET_ACTIVE + " = ? WHERE "
                    + ASSET_ID + " = ?;";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private static final String GET_ASSETNAMES = "SELECT " + ASSET_NAME + " FROM assets";
    private static final String GET_ASSET = "SELECT * FROM assets WHERE " + ASSET_NAME + "=?";
    private static final String GET_ASSET_INT = "SELECT * FROM assets WHERE " + ASSET_ID + "=?";
    private static final String DELETE_ASSET = "DELETE FROM assets WHERE " + ASSET_NAME + "=?";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM assets";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private Connection connection;
    private PreparedStatement addAsset;
    private PreparedStatement modifyAsset;
    private PreparedStatement getAssetNameList;
    private PreparedStatement getAsset;
    private PreparedStatement getAssetID;
    private PreparedStatement deleteAsset;
    private PreparedStatement rowCount;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public JDBCAssetDataSource()
    {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            addAsset = connection.prepareStatement(INSERT_ASSET);
            modifyAsset = connection.prepareStatement(MODIFY_ASSET);
            getAssetNameList = connection.prepareStatement(GET_ASSETNAMES);
            getAsset = connection.prepareStatement(GET_ASSET);
            getAssetID = connection.prepareStatement(GET_ASSET_INT);
            deleteAsset = connection.prepareStatement(DELETE_ASSET);
            rowCount = connection.prepareStatement(COUNT_ROWS);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see AssetDataSource#addAsset(Asset)
     */
    public void addAsset(Asset a) {
        try {
            addAsset.setInt(1, a.getAssetID());
            addAsset.setString(2, a.getAssetName());
            addAsset.setString(3, a.getAssetDescription());
            addAsset.setString(4, a.getAssetCategory());
            addAsset.setBoolean(5, a.getAssetActive());
            addAsset.setString(6, a.getAssetColorValue());
            addAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see AssetDataSource#modifyAsset(Asset)
     */
    public void modifyAsset(Asset a) {
        try {
            modifyAsset.setInt(5, a.getAssetID());
            modifyAsset.setString(1, a.getAssetName());
            modifyAsset.setString(2, a.getAssetDescription());
            modifyAsset.setString(3, a.getAssetCategory());
            modifyAsset.setBoolean(4, a.getAssetActive());
            modifyAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     //* @see Objectsnew.AssetDataSource#assetNameSet()
     */
    public Set<String> assetNameSet() {
        Set<String> assetNames = new TreeSet<String>();
        ResultSet rs = null;

        try {
            rs = getAssetNameList.executeQuery();
            while (rs.next()) {
                assetNames.add(rs.getString(ASSET_NAME));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return assetNames;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see AssetDataSource#getAsset(String)
     */
    public Asset getAsset(String assetName) {
        Asset a = new Asset();
        ResultSet rs;

        try {
            getAsset.setString(1, assetName);
            rs = getAsset.executeQuery();
            rs.next();
            a.setAssetID(rs.getInt(ASSET_ID));
            a.setAssetName(rs.getString(ASSET_NAME));
            a.setAssetDescription(rs.getString(ASSET_DESCRIPTION));
            a.setAssetCategory(rs.getString(ASSET_CATEGORY));
            a.setAssetActive(rs.getBoolean(ASSET_ACTIVE));
            a.setAssetColourValue(rs.getString(ASSET_COLOUR));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see AssetDataSource#getAsset(Integer)
     */
    public Asset getAsset(Integer assetID) {
        Asset a = new Asset();
        ResultSet rs;

        try {
            getAssetID.setInt(1, assetID);
            rs = getAssetID.executeQuery();
            rs.next();
            a.setAssetID(rs.getInt(ASSET_ID));
            a.setAssetName(rs.getString(ASSET_NAME));
            a.setAssetDescription(rs.getString(ASSET_DESCRIPTION));
            a.setAssetCategory(rs.getString(ASSET_CATEGORY));
            a.setAssetActive(rs.getBoolean(ASSET_ACTIVE));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see AssetDataSource#getSize()
     */
    public int getSize() {
        ResultSet rs;
        int rows = 0;

        try {
            rs = rowCount.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rows;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see AssetDataSource#deleteAsset(String)
     */
    public void deleteAsset(String assetName) {
        try {
            deleteAsset.setString(1, assetName);
            deleteAsset.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see AssetDataSource#close()
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
