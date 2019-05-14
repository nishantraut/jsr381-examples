package jsr381.example;

import deepnetts.data.DataSet;
import deepnetts.data.DataSets;
import jsr381.example.util.DataSetExamples;
import visrec.ri.ml.classification.DeepNettsMultiClassClassifier;

import javax.visrec.ml.classification.MultiClassClassifier;
import java.io.IOException;
import java.util.Map;

public class IrisClassificationExample {
    public static void main(String[] args) throws IOException {

        // load iris data set
        DataSet dataSet = DataSetExamples.getIrisClassificationDataSet();
        DataSet[] trainTestSplit = DataSets.trainTestSplit(dataSet, 0.7);

        // build multi class classifier using deep netts implementation of feed forward network under the hood
        MultiClassClassifier classifier = DeepNettsMultiClassClassifier.builder()
                                                        .inputsNum(4)
                                                        .hiddenLayers(16)                       // if its a common thing put it in the API
                                                        .outputsNum(3)                          //I think its ok to use implementation specific type since this is specific impl
                                                        .maxEpochs(10000)
                                                        .maxError(0.05f)
                                                        .learningRate(0.01f)
                                                        .trainingSet(trainTestSplit[0])
                                                        .build();

        // use classifier to predict class
        Map<String, Float> results = classifier.classify(args);
        System.out.println(results);
        // if training set is specified perform training when build is invoked, otherwice invoke train separetly from building, see bellow
        // if test set is specified also perform performance evalaution
        // performe entire standard ml workflow with meaningfull error messages if something is not right
        // we are not assuming that user know data prep, ml and stats
        // croccvalidation?

      //  classifier.train(trainAndTestSet[0]);
  //      classifier.test(trainAndTestSet[1]);   // do this internally after training, log results, and return Map with performance measures?

    }
}
