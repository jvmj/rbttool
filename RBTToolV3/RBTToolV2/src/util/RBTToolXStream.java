package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;

import essentials.Equation;
import essentials.IdentifiedRisk;
import essentials.Metric;
import essentials.Project;
import essentials.Question;
import essentials.QuestionAnswer;
import essentials.Questionnaire;
import essentials.Requirement;
import essentials.Risk;
import essentials.RiskAnalysis;
import essentials.RiskManagementReport;
import essentials.Scenario;
import essentials.TestCase;
import essentials.TestCaseProcedure;
import essentials.TestCaseStatus;
import essentials.TestIteration;
import essentials.TestPlan;

public class RBTToolXStream {

	private static RBTToolXStream rbttoolXtream;
	private XStream xstream;

	private RBTToolXStream() {
		super();
		this.xstream = new XStream();
		// Definindo ALIAS
		xstream.alias("rbttool", Vector.class);
		xstream.alias("requirement", Requirement.class);
		xstream.alias("project", Project.class);
		xstream.alias("scenario", Scenario.class);
		xstream.alias("metric", Metric.class);
		xstream.alias("risk", Risk.class);
		xstream.alias("question", Question.class);
		xstream.alias("identifiedRisk", IdentifiedRisk.class);
		xstream.alias("questionAnswer", QuestionAnswer.class);
		xstream.alias("questionAnswerAssociation", HashMap.class);
		xstream.alias("questionnaire", Questionnaire.class);
		xstream.alias("equation", Equation.class);
		xstream.alias("metric", Metric.class);
		xstream.alias("riskAnalysis", RiskAnalysis.class);
		xstream.alias("riskManagementReport", RiskManagementReport.class);
		xstream.alias("testPlan", TestPlan.class);
		xstream.alias("testIteration", TestIteration.class);
		xstream.alias("testCase", TestCase.class);
		xstream.alias("testCaseStatus", TestCaseStatus.class);
		xstream.alias("testCaseProcedure", TestCaseProcedure.class);
	}

	public static RBTToolXStream getInstance() {
		if (rbttoolXtream == null) {
			rbttoolXtream = new RBTToolXStream();
		}
		return rbttoolXtream;
	}

	public Vector<Object> readXML(String caminho, String nomeArquivo) {
		File lendo = new File(caminho, nomeArquivo);
		
		if (!lendo.exists()) {
			return null;
		}
		
		Vector<Object> objetos = new Vector<Object>();
		try {
			FileReader fr = new FileReader(lendo);
			BufferedReader br = new BufferedReader(fr);

			// Passando os dados de XML para Objetos Java
			objetos = (Vector<Object>) xstream.fromXML(br);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return objetos;
	}
	
	public void writeXML(String caminho, String nomeArquivo, Vector<Object> dados){
		// Passando os dados de Objetos Java para XML
        String dadosXML = xstream.toXML(dados);
		
		try {
            // Persistindo em arquivo
            File dir = new File(caminho); // Define o diretorio

            File file = new File(dir, nomeArquivo); // Define o arquivo no diretorio
            dir.mkdir();// cria o diretorio			

            PrintWriter pw = new PrintWriter(file);
            pw.write(dadosXML);
            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}
