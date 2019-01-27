package cralwer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.opencsv.*;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.SerializationHelper;

public class Weka_Api {

	private String[] header_categorize = { "Tweet", "Lable" };
	private String[] header_Real_Bot = { "Max_of_Tweets_in_DAY", "avg_number_of_Hashtag", "avg_URL", "followers_count",
			"friends_count", "geo_enabled", "verified", "after_Midnight","morning", "after_Noon", "evening","source", "Label" };
	private String [] Lable_categorize = {"other","social","advertisement","religion","news","sport","politics","health","technology","economy","Entertainment"};
	private String [] Lable_classify= {"Real","Bot"};
	
	public final String PATH_MODEL_categories_model = "/Users/Donat/Desktop/Final_Project/PHASE_2/GIT/project/ServletProjects/data/categories.model";
	public final String PATH_MODEL_Real_Bot_souece = "/Users/Donat/Desktop/Final_Project/PHASE_2/GIT/project/ServletProjects/data/Real_Bot_souece.model";
	public final String PATH_CSV_CATEGORIES = "/Users/Donat/Desktop/Final_Project/PHASE_2/GIT/project/ServletProjects/data/csv/test.csv";
	public final String PATH_CSV_REAL_BOT = "/Users/Donat/Desktop/Final_Project/PHASE_2/GIT/project/ServletProjects/data/csv/testy.csv";

	public void writeDataLineByLine(String filePath, String[] header, String[] data) {
		// first create file object for file placed at location
		// specified by filepath
		File file = new File(filePath);
		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);

			// adding header to csv
			writer.writeNext(header);

			// add data to csv
			writer.writeNext(data);
			// closing writer connection
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String replace_Unwantedchar(String str) {
		char newLine =10, quote=34 ,comma=',',ubcomma='\'',pip='|',point='…',minusedown='_',CR=13;
	    char FATHATEN = '\u064B';
	    char DAMMATEN = '\u064C';
	    char KASRATEN = '\u064D';
	    char FATHA = '\u064E';
		char DAMMA = '\u064F';
		char KASRA = '\u0650';
	    char SHADDA = '\u0651';
        char SUKUN = '\u0652';
		str=str.replace(newLine, ' ');
		str=str.replace(quote, ' ');
		str=str.replace(comma, ' ');
		str=str.replace(FATHATEN, ' ');
		str=str.replace(DAMMATEN, ' ');
		str=str.replace(KASRATEN, ' ');
		str=str.replace(FATHA, ' ');
		str=str.replace(DAMMA, ' ');
		str=str.replace(KASRA, ' ');
		str=str.replace(SHADDA, ' ');
		str=str.replace(SUKUN, ' ');
		str=str.replace(ubcomma, ' ');
		str=str.replace(pip, ' ');
		str=str.replace(point, ' ');
		str=str.replace(minusedown, ' ');
		str=str.replace(CR, ' ');	
		return str;
	}
	public LinkedList<Tweet_Lable> categorize(LinkedList<Tweet_Lable> list) {
		String path_of_Modle = PATH_MODEL_categories_model, Path_File = PATH_CSV_CATEGORIES;
		String tmp;
  
		try {
			File file = new File(Path_File);
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);
			writer.writeNext(header_categorize);
			for (Tweet_Lable l : list) {
				// adding header to csv
				// add data to csv
				tmp=l.getText();
				tmp=replace_Unwantedchar(tmp);
				l.setText(tmp);
				l.setLable("Uncategorize");// You must add to the label brat   
				String[] data = { l.getText(),l.getLable() };
				writer.writeNext(data);
			}
			// closing writer connection
			writer.close();

			FilteredClassifier smo2 = (FilteredClassifier) weka.core.SerializationHelper.read(path_of_Modle);

			// load new dataset
			DataSource source1 = new DataSource(Path_File);
			Instances testDataset = source1.getDataSet();
			
			// set class index to the last attribute
			testDataset.setClassIndex(testDataset.numAttributes() - 1);

			// get Instance object of first instance
			Instance newInst = null;
			double results;
			String Lable = "";
			for (int j = 0; j < list.size(); j++) {
				newInst = testDataset.instance(j);
				results = smo2.classifyInstance(newInst);
				Lable = Lable_categorize[(int)results];
				list.get(j).setLable(Lable);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public User_Lable classify(User_Lable user) {

		String path_of_Modle = PATH_MODEL_Real_Bot_souece, Path_File = PATH_CSV_REAL_BOT;
		try {
			File file = new File(Path_File);
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);

			// adding header to csv
			writer.writeNext(header_Real_Bot);
			// add data to csv
			String[] data = { Integer.toString(user.getMax_of_Tweets_in_DAY()),
					Double.toString(user.getAvg_number_of_Hashtag()), Double.toString(user.getAvg_URL()),
					Integer.toString(user.getGeo_enabled()), Integer.toString(user.getVerified()),
					Integer.toString(user.getFollowers_count()),Integer.toString(user.getFriends_count()),
					Integer.toString(user.getAfter_Midnight()), Integer.toString(user.getMorning()),
					Integer.toString(user.getAfter_Noon()), Integer.toString(user.getEvening()),user.getSource(),"Unclassified" };
			writer.writeNext(data);

			// closing writer connection
			writer.close();
//
			FilteredClassifier smo2 = (FilteredClassifier) weka.core.SerializationHelper.read(path_of_Modle);

			// load new dataset
			DataSource source1 = new DataSource(Path_File);
			Instances testDataset = source1.getDataSet();
			// set class index to the last attribute
			testDataset.setClassIndex(testDataset.numAttributes() - 1);

			// get Instance object of first instance
			Instance newInst = null;
			double results = -1;
			String Lable = "";
			newInst = testDataset.instance(0);
			results = smo2.classifyInstance(newInst); // classify result
			Lable = Lable_classify[(int)results];
			user.setLable(Lable);
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}
}