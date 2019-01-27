package cralwer;
 
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Main_ML {

	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub

//		
//		BufferedReader source = new BufferedReader(new FileReader("/Users/brookalharthi/Desktop/Level8/497csc/Database/Trning_set_and_test/Trning/balenceSTRING2.csv.arff"));
//		Instances dataset = new Instances(source);
//		
//		dataset.setClassIndex(dataset.numAttributes()-1);
//		
//		source.close();
//		NaiveBayes nb = new NaiveBayes();
//		nb.buildClassifier(dataset);
		
		
//	    //the filter
//	    StringToWordVector filter = new StringToWordVector();
//	    filter.setInputFormat(dataset);
//	    weka.core.stemmers.
//	    LovinsStemmer stemmer = new LovinsStemmer();
//	    filter.setStemmer(stemmer);
//	    filter.setLowerCaseTokens(true);
//
//	    //Create the FilteredClassifier object
//	    FilteredClassifier fc = new FilteredClassifier();
//	    //specify filter
//	    fc.setFilter(filter);
//	    //specify base classifier
//	    fc.setClassifier(tree);
//	    //Build the meta-classifier
//	    fc.buildClassifier(dataset);
//		Evaluation eval = new Evaluation(dataset);
//		eval.evaluateModel(nb, dataset);
//		System.out.println(eval.toSummaryString());
		
//		J48 svm = new J48();
//		svm.buildClassifier(train);
//		
		
		
	//	FilteredClassifier smo2 = (FilteredClassifier) weka.core.SerializationHelper.read("data/RandomForst.model");
		
//		System.out.println(		smo2.batchSizeTipText());
//	    System.out.println();
//		System.out.println(		smo2.classifierTipText());
//		 System.out.println();
//		System.out.println(smo2.globalInfo());
//		 System.out.println();
//		System.out.println(smo2.numElements());

//		
//
		
		FilteredClassifier smo2 = (FilteredClassifier) weka.core.SerializationHelper.read("data/Freal.model");

		//load new dataset
		DataSource source1 = new DataSource("/Users/brookalharthi/Desktop/Level8/497csc/Database/Trning_set_and_test/Trning/Real_Bot.csv");
		Instances testDataset = source1.getDataSet();	
//		
////		
		//set class index to the last attribute
		testDataset.setClassIndex(testDataset.numAttributes()-1);
		//get class double value for first instance

		//System.out.println(testDataset.attribute("Lable").value(1));
		double actualValue;
		//get Instance object of first instance
	    Instance newInst = null;
	    double predSMO=-1;
	    	for (int i = 0; i < 20; i++) {
	    		actualValue = testDataset.instance(i).classValue();
				newInst = testDataset.instance(i);
				
//				double [] results = smo2.distributionForInstance(newInst);
//				System.out.println("Prob for 1 is: "+ results[0] + " prob for 2 is: "+ results[1]);
				
				predSMO=  smo2.classifyInstance(newInst);			
				System.out.println(actualValue+", "+predSMO);
			}
//			
			
		
		//call classifyInstance, which returns a double value for the class
		

//		Evaluation eval = new Evaluation(train);
//		
//		eval.crossValidateModel(svm, train, 10, new Random(1));
//		System.out.println(eval.toSummaryString("\nResults\n======\n",true));
	//	System.out.println(eval.toSummaryString());
		
//		LibSVM libsvm = new LibSVM();
//		String[] options = new String[8];
//		options[0] = "-S"; options[1] = "0";
//		options[2] ="-K"; options[3] = "2";
//		options[4] = "-G"; options[5] = "1.0";
//		options[6] = "-C"; options[7] = "1.0";
//	    libsvm.setOptions(options);
//		libsvm.buildClassifier(dataset);
//		Evaluation eval3 = new Evaluation(dataset);
//		eval3.evaluateModel(libsvm, dataset);
//		System.out.println(eval3.toSummaryString());
		
	}

}
