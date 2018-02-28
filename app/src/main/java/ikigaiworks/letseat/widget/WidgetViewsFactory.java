package ikigaiworks.letseat.widget; /***
 Copyright (c) 2008-2012 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 From _The Busy Coder's Guide to Advanced Android Development_
 http://commonsware.com/AdvAndroid
 */


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.utils.FavoriteUtils;

import static ikigaiworks.letseat.widget.WidgetProvider.WIDGET_ROW_ONCLICK;

public class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context ctxt=null;
    private int appWidgetId;
    List<FavOrder> list;

    public WidgetViewsFactory(Context ctxt, Intent intent) {
        this.ctxt=ctxt;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        LinkedHashMap<String,FavOrder> favs = FavoriteUtils.getFavList();
        list = new ArrayList<FavOrder>(favs.values());
    }

    @Override
    public void onCreate() {
        // no-op
    }

    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row=new RemoteViews(ctxt.getPackageName(),
                R.layout.widget_row);

        row.setTextViewText(R.id.name, list.get(position).getName());
        row.setTextViewText(R.id.date, list.get(position).getDateFormated());

        Intent fillInIntent = new Intent();
        fillInIntent.putExtra(WIDGET_ROW_ONCLICK, list.get(position));
        row.setOnClickFillInIntent(R.id.widget_row, fillInIntent);

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return(null);
    }

    @Override
    public int getViewTypeCount() {
        return(1);
    }

    @Override
    public long getItemId(int position) {
        return(position);
    }

    @Override
    public boolean hasStableIds() {
        return(true);
    }

    @Override
    public void onDataSetChanged() {
        // no-op
    }
}