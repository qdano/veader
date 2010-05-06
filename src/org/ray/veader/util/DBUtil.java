package org.ray.veader.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import java.io.File;
import java.util.Date;

import org.ray.veader.provider.BookColumn;

public class DBUtil {

	private static Date getDate(Cursor cursor, int index) {
		if (cursor.isNull(index)) {
			return null;
		}
		return new Date(cursor.getLong(index));
	}
    public static void clearFileNoFound(Context context) {
        Cursor cursor = context.getContentResolver().query(
                BookColumn.CONTENT_URI,
                new String[] {BookColumn._ID, BookColumn.PATH}, null, null,
                null);
        cursor.deactivate();
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor
                    .getColumnIndexOrThrow(BookColumn.PATH));
            File file = new File(path);
            if (!file.exists()) {
                String id = cursor.getString(cursor
                        .getColumnIndexOrThrow(BookColumn._ID));
                Uri delUri = Uri.parse(BookColumn.CONTENT_URI + "/" + id);
                context.getContentResolver().delete(delUri, null, null);
            }
        }

        cursor.close();

    }

    public static void clearAllBooks(Context context) {
        context.getContentResolver().delete(BookColumn.CONTENT_URI, null, null);
    }
    
    

    public static boolean isExits(Context context, File path) {
        String pathOption = BookColumn.PATH + " = '" + path.getAbsolutePath()
                + "'";
        Cursor cursor = context.getContentResolver().query(
                BookColumn.CONTENT_URI, new String[] {BookColumn._ID},
                pathOption, null, null);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
    
}
