package com.freelanceProject.lavoiedroite.Adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androbito.web.ImageDownloader;
import com.freelanceProject.lavoiedroite.R;
import com.freelanceProject.lavoiedroite.beans.Categorie;
import com.freelanceProject.lavoiedroite.beans.Theme;

public class ThemeAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<Categorie> listGroupe;
	private ImageDownloader imgDown;

	public ThemeAdapter(Context _context, List<Categorie> listCategories) {
		this._context = _context;
		this.imgDown = new ImageDownloader();
		this.listGroupe = new ArrayList<Categorie>(0);
		this.listGroupe.addAll(listCategories);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.listGroupe.get(groupPosition).getThemes()
				.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String childText = ((Theme) getChild(groupPosition, childPosition))
				.getNameTheme();

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.theme_row, null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.txt_theme);

		txtListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.listGroupe.get(groupPosition).getThemes().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listGroupe.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listGroupe.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		final String groupText = ((Categorie) getGroup(groupPosition))
				.getDetailCategoie();
		final String groupIcone = ((Categorie) getGroup(groupPosition))
				.getIcone();
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.categorie_group, null);
		}
		TextView txtListCategorie = (TextView) convertView
				.findViewById(R.id.txt_categorie);
		ProgressBar pb = (ProgressBar) convertView.findViewById(R.id.PBTheme);
		ImageView img = (ImageView) convertView.findViewById(R.id.ThemeIcone);
		txtListCategorie.setText(groupText);
		imgDown.download(groupIcone, img, pb);
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
