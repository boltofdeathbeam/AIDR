package qa.qcri.aidr.predictui.facade.imp;

import qa.qcri.aidr.common.exception.PropertyNotSetException;
import qa.qcri.aidr.common.logging.ErrorLog;
import qa.qcri.aidr.dbmanager.dto.DocumentDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qa.qcri.aidr.predictui.entities.Crisis;
import qa.qcri.aidr.predictui.entities.Document;
import qa.qcri.aidr.predictui.facade.DocumentFacade;
import qa.qcri.aidr.task.ejb.TaskManagerRemote;

/**
 *
 * @author koushik
 */
@Stateless
public class DocumentFacadeImp implements DocumentFacade{

	//@PersistenceContext(unitName = "qa.qcri.aidr.predictui-EJBS")
	//private EntityManager em;

	@EJB
	private TaskManagerRemote remoteTaskManager;
	
	protected static Logger logger = LoggerFactory.getLogger("aidr-tagger-api");
	
	public List<DocumentDTO> getAllDocuments() {
		List<DocumentDTO> fetchedList = remoteTaskManager.getAllTasks();
		return fetchedList;   
	}

	public DocumentDTO getDocumentByID(Long id) {
		DocumentDTO fetchedDoc  = remoteTaskManager.getTaskById(id);
		return fetchedDoc;
	}

	public List<DocumentDTO> getAllLabeledDocumentbyCrisisID(Long crisisID, Long attributeID) {

		Criterion criterion = Restrictions.eq("hasHumanLabels", true);
		List<DocumentDTO> fetchedList = remoteTaskManager.getTaskCollectionByCriterion(crisisID, null, criterion);
		
		return fetchedList;
	}

	@Override
	public int deleteDocument(Long documentID) {
		return remoteTaskManager.deleteTaskById(documentID);
	}

	@Override
	public void removeTrainingExample(Long documentID) {
		// Alternative way of doing the same update
		//qa.qcri.aidr.task.dto.DocumentDTO fetchedDoc  = remoteTaskManager.getTaskById(id);
		//fetchedDoc.setHasHumanLabels(false);
		//fetchedDoc.setNominalLabelCollection(null);
		//taskManager.updateTask(fetchedDoc);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("setHasHumanLabels", new Boolean(false).toString());
		paramMap.put("setNominalLabelCollection", null);
		DocumentDTO newDoc = (DocumentDTO) remoteTaskManager.setTaskParameter(qa.qcri.aidr.dbmanager.entities.task.Document.class, documentID, paramMap);
	
		try {
			logger.info("Removed training example: " + newDoc.getDocumentID() + ", for crisisID = " + newDoc.getCrisisDTO().getCrisisID());
		} catch (PropertyNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<DocumentDTO> getAllUnlabeledDocumentbyCrisisID(Long crisisID) {
		Criterion criterion = Restrictions.eq("hasHumanLabels", false);
		List<DocumentDTO> fetchedList = remoteTaskManager.getTaskCollectionByCriterion(crisisID, null, criterion);
		
		return fetchedList;
	}

}
