package com.example.a25thang3.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.a25thang3.model.Category;
import com.example.a25thang3.model.Order;
import com.example.a25thang3.model.OrderDetail;
import com.example.a25thang3.model.Product;
import com.example.a25thang3.model.User;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDao_Impl implements AppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityInsertionAdapter<Category> __insertionAdapterOfCategory;

  private final EntityInsertionAdapter<Product> __insertionAdapterOfProduct;

  private final EntityInsertionAdapter<Order> __insertionAdapterOfOrder;

  private final EntityInsertionAdapter<OrderDetail> __insertionAdapterOfOrderDetail;

  private final EntityDeletionOrUpdateAdapter<Order> __updateAdapterOfOrder;

  public AppDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `users` (`id`,`username`,`password`,`fullName`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.id);
        if (entity.username == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.username);
        }
        if (entity.password == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.password);
        }
        if (entity.fullName == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.fullName);
        }
      }
    };
    this.__insertionAdapterOfCategory = new EntityInsertionAdapter<Category>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `categories` (`id`,`name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Category entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
      }
    };
    this.__insertionAdapterOfProduct = new EntityInsertionAdapter<Product>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `products` (`id`,`name`,`price`,`description`,`categoryId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Product entity) {
        statement.bindLong(1, entity.id);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        statement.bindDouble(3, entity.price);
        if (entity.description == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.description);
        }
        statement.bindLong(5, entity.categoryId);
      }
    };
    this.__insertionAdapterOfOrder = new EntityInsertionAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `orders` (`id`,`userId`,`orderDate`,`isPaid`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.userId);
        if (entity.orderDate == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.orderDate);
        }
        final int _tmp = entity.isPaid ? 1 : 0;
        statement.bindLong(4, _tmp);
      }
    };
    this.__insertionAdapterOfOrderDetail = new EntityInsertionAdapter<OrderDetail>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `order_details` (`id`,`orderId`,`productId`,`quantity`,`price`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final OrderDetail entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.orderId);
        statement.bindLong(3, entity.productId);
        statement.bindLong(4, entity.quantity);
        statement.bindDouble(5, entity.price);
      }
    };
    this.__updateAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `orders` SET `id` = ?,`userId` = ?,`orderDate` = ?,`isPaid` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.id);
        statement.bindLong(2, entity.userId);
        if (entity.orderDate == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.orderDate);
        }
        final int _tmp = entity.isPaid ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.id);
      }
    };
  }

  @Override
  public long insertUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfUser.insertAndReturnId(user);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertCategory(final Category category) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCategory.insert(category);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertProduct(final Product product) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfProduct.insert(product);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insertOrder(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final long _result = __insertionAdapterOfOrder.insertAndReturnId(order);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOrderDetail(final OrderDetail orderDetail) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrderDetail.insert(orderDetail);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateOrder(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOrder.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public User login(final String username, final String password) {
    final String _sql = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
      final User _result;
      if (_cursor.moveToFirst()) {
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _result = new User(_tmpUsername,_tmpPassword,_tmpFullName);
        _result.id = _cursor.getInt(_cursorIndexOfId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserById(final int userId) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
      final User _result;
      if (_cursor.moveToFirst()) {
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _result = new User(_tmpUsername,_tmpPassword,_tmpFullName);
        _result.id = _cursor.getInt(_cursorIndexOfId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Category> getAllCategories() {
    final String _sql = "SELECT * FROM categories";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Category _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item = new Category(_tmpName);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Product> getAllProducts() {
    final String _sql = "SELECT * FROM products";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
      final List<Product> _result = new ArrayList<Product>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Product _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _item = new Product(_tmpName,_tmpPrice,_tmpDescription,_tmpCategoryId);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Product> getProductsByCategory(final int categoryId) {
    final String _sql = "SELECT * FROM products WHERE categoryId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
      final List<Product> _result = new ArrayList<Product>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Product _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _item = new Product(_tmpName,_tmpPrice,_tmpDescription,_tmpCategoryId);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Product getProductById(final int productId) {
    final String _sql = "SELECT * FROM products WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
      final Product _result;
      if (_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _result = new Product(_tmpName,_tmpPrice,_tmpDescription,_tmpCategoryId);
        _result.id = _cursor.getInt(_cursorIndexOfId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Order getUnpaidOrderByUser(final int userId) {
    final String _sql = "SELECT * FROM orders WHERE userId = ? AND isPaid = 0 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfOrderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "orderDate");
      final int _cursorIndexOfIsPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaid");
      final Order _result;
      if (_cursor.moveToFirst()) {
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final String _tmpOrderDate;
        if (_cursor.isNull(_cursorIndexOfOrderDate)) {
          _tmpOrderDate = null;
        } else {
          _tmpOrderDate = _cursor.getString(_cursorIndexOfOrderDate);
        }
        final boolean _tmpIsPaid;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsPaid);
        _tmpIsPaid = _tmp != 0;
        _result = new Order(_tmpUserId,_tmpOrderDate,_tmpIsPaid);
        _result.id = _cursor.getInt(_cursorIndexOfId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Order getLastPaidOrderByUser(final int userId) {
    final String _sql = "SELECT * FROM orders WHERE userId = ? AND isPaid = 1 ORDER BY id DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfOrderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "orderDate");
      final int _cursorIndexOfIsPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaid");
      final Order _result;
      if (_cursor.moveToFirst()) {
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final String _tmpOrderDate;
        if (_cursor.isNull(_cursorIndexOfOrderDate)) {
          _tmpOrderDate = null;
        } else {
          _tmpOrderDate = _cursor.getString(_cursorIndexOfOrderDate);
        }
        final boolean _tmpIsPaid;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsPaid);
        _tmpIsPaid = _tmp != 0;
        _result = new Order(_tmpUserId,_tmpOrderDate,_tmpIsPaid);
        _result.id = _cursor.getInt(_cursorIndexOfId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Order getOrderById(final int orderId) {
    final String _sql = "SELECT * FROM orders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfOrderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "orderDate");
      final int _cursorIndexOfIsPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaid");
      final Order _result;
      if (_cursor.moveToFirst()) {
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final String _tmpOrderDate;
        if (_cursor.isNull(_cursorIndexOfOrderDate)) {
          _tmpOrderDate = null;
        } else {
          _tmpOrderDate = _cursor.getString(_cursorIndexOfOrderDate);
        }
        final boolean _tmpIsPaid;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsPaid);
        _tmpIsPaid = _tmp != 0;
        _result = new Order(_tmpUserId,_tmpOrderDate,_tmpIsPaid);
        _result.id = _cursor.getInt(_cursorIndexOfId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<OrderDetail> getOrderDetailsByOrder(final int orderId) {
    final String _sql = "SELECT * FROM order_details WHERE orderId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final List<OrderDetail> _result = new ArrayList<OrderDetail>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final OrderDetail _item;
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        final int _tmpProductId;
        _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
        final int _tmpQuantity;
        _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item = new OrderDetail(_tmpOrderId,_tmpProductId,_tmpQuantity,_tmpPrice);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
