package com.freelanceProject.lavoiedroite.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Themes {
	@SerializedName("2")
	public ArrayList<Theme> coranCat;
	@SerializedName("3")
	public ArrayList<Theme> hadithsCat;
	@SerializedName("4")
	public ArrayList<Theme> croyanceCat;
	@SerializedName("6")
	public ArrayList<Theme> comportementsCat;
	@SerializedName("18")
	public ArrayList<Theme> jurisprudenceCat;
	@SerializedName("7")
	public ArrayList<Theme> histoiresCat;
	@SerializedName("59")
	public ArrayList<Theme> evenementsCat;
	@SerializedName("68")
	public ArrayList<Theme> familleCat;
	@SerializedName("64")
	public ArrayList<Theme> sectesgroupesCat;
	@SerializedName("73")
	public ArrayList<Theme> diversCat;

	public Map<Integer, ArrayList<Theme>> getThemesByCategorie() {
		Map<Integer, ArrayList<Theme>> mapThemes = new HashMap<Integer, ArrayList<Theme>>();
		mapThemes.put(2, coranCat);
		mapThemes.put(3, hadithsCat);
		mapThemes.put(4, croyanceCat);
		mapThemes.put(6, comportementsCat);
		mapThemes.put(7, histoiresCat);
		mapThemes.put(18, jurisprudenceCat);
		mapThemes.put(59, evenementsCat);
		mapThemes.put(64, sectesgroupesCat);
		mapThemes.put(68, familleCat);
		mapThemes.put(73, diversCat);

		return mapThemes;

	}
}
