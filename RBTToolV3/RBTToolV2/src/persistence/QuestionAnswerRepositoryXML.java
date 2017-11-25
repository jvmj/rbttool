package persistence;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import persistence.interfaces.IQuestionAnswerRepository;

import util.FilesUtil;
import util.RBTToolXStream;
import essentials.QuestionAnswer;

public class QuestionAnswerRepositoryXML implements IQuestionAnswerRepository{

	@Override
	public void saveQuestionAnswer(QuestionAnswer questionAnswer) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.ANSWERSPATH, FilesUtil.ANSWERSFILENAME);
		if (objetos == null) {
			objetos = new Vector<Object>();
		}
		objetos.addElement(questionAnswer);
		rbtToolXStream.writeXML(FilesUtil.ANSWERSPATH, FilesUtil.ANSWERSFILENAME, objetos);
		
	}
	
	public Vector<Object> getQuestionAnswers() {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.ANSWERSPATH, FilesUtil.ANSWERSFILENAME);
		return objetos;
	}
	
	public Vector<Object> getImportedQuestionAnswers() {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.IMPORTEDANSWERSPATH, FilesUtil.IMPORTEDANSWERSFILENAME);
		return objetos;
	}
	
	public boolean exportQuestionAnswer(QuestionAnswer questionAnswer, File file){
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos  = new Vector<Object>();
		
		objetos.addElement(questionAnswer);
		rbtToolXStream.writeXML(file.getParent(), file.getName(), objetos);
		return true;
	}
	
	public boolean importQuestionAnswerFile(File file){
        RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
        Vector<Object> objetos  = new Vector<Object>();
        Vector<Object> objetosFinal  = new Vector<Object>();
        objetos = rbtToolXStream.readXML(file.getParent(), file.getName());
        if (objetos != null) {
            objetosFinal = rbtToolXStream.readXML(FilesUtil.IMPORTEDANSWERSPATH, FilesUtil.IMPORTEDANSWERSFILENAME);
            if (objetosFinal == null) {
                objetosFinal = new Vector<Object>();
            }
            objetosFinal.addAll(objetos);
            rbtToolXStream.writeXML(FilesUtil.IMPORTEDANSWERSPATH, FilesUtil.IMPORTEDANSWERSFILENAME, objetosFinal);
            return true;
        }else {
            return false;
        }        
    } 

	@Override
	public QuestionAnswer searchQuestionAnswer(String questionAnswerID) {
		RBTToolXStream rbtToolXStream = RBTToolXStream.getInstance();
		Vector<Object> objetos = rbtToolXStream.readXML(FilesUtil.ANSWERSPATH, FilesUtil.ANSWERSFILENAME);
		if (objetos != null && objetos.size() != 0) {
			for (Iterator iterator = objetos.iterator(); iterator.hasNext();) {
				QuestionAnswer questionAnswer = (QuestionAnswer) iterator.next();
				if (questionAnswer.getQuestionAnswerID().equals(questionAnswerID)) {
					return questionAnswer;
				}
			}
		} 
		return null;
	}

}
