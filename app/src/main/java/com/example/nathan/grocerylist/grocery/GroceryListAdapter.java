package com.example.nathan.grocerylist.grocery;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.nathan.grocerylist.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nathan on 10/31/2015.
 */
public class GroceryListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;
    private ArrayList<GroceryItem> _listItems;

    public GroceryListAdapter(Context context, GroceryList list) {
        this._context = context;
        this._listItems = list.getItems();
        this._listDataHeader = new ArrayList<String>();
        this._listDataChild = new HashMap<String, List<String>>();
        this.prepareListDataHeader();
        this.prepareListDataChild();
    }

    private void prepareListDataHeader() {
        for (GroceryItem item: this._listItems) {
            String section = item.getSection();
            if (!this._listDataHeader.contains(section)) {
                this._listDataHeader.add(section);
            }
        }
    }

    private void prepareListDataChild() {
        for (String section: this._listDataHeader) {
            List<String> section_items = new ArrayList<String>();
            for (GroceryItem item: this._listItems) {
                if (item.getSection() == section) {
                    section_items.add(item.getName());
                }
            }
            this._listDataChild.put(section, section_items);
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.groceryListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.groceryListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
