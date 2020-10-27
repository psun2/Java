package com.command.write;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lec.beans.AjaxBoardListJSON;
import com.lec.beans.AjaxBoradListXML;
import com.lec.beans.WriteDTO;

public class AjaxListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// parameter 받아오기, 없으면 json 으로 response(default)
		String reqType = request.getParameter("reqType");

		if (reqType == null)
			reqType = "json";

		// "xml" 혹은 "json" 으로 response 하기
		switch (reqType) {

		case "xml":
//			responseXML(request, response); // Jdom 사용하여 xml response
			responseXML2(request, response); // 잭슨 사용
			break;

		case "json":
//			responseJSON(request, response); // org.json 사용
			responseJSON2(request, response); // 잭슨 사용
			break;

		default:
			responseJSON(request, response); // org.json 사용
			break;

		} // end switch
	} // end execute()

	// jackson json 사용
	private void responseJSON2(HttpServletRequest request, HttpServletResponse response) {
		// ListCommand 의 request에 실린 list 받아오기
		WriteDTO[] dtoArr = (WriteDTO[]) request.getAttribute("list");

		AjaxBoardListJSON list = new AjaxBoardListJSON(); // response 할 자바객체

		if (dtoArr != null) {
			list.setCount(dtoArr.length);
			list.setStatus("OK");
			list.setList(Arrays.asList(dtoArr));
		} else {
			list.setCount(0);
			list.setStatus("FAIL");
		}
		
		try {
			JsonMapper mapper = new JsonMapper();
			String json = mapper.writeValueAsString(list);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// jackson xml 사용
	private void responseXML2(HttpServletRequest request, HttpServletResponse response) {
		// ListCommand 의 request에 실린 list 받아오기
		WriteDTO[] dtoArr = (WriteDTO[]) request.getAttribute("list");

		AjaxBoradListXML list = new AjaxBoradListXML(); // response 할 자바 객체

		if (dtoArr != null) {
			list.setCount(dtoArr.length);
			list.setStatus("OK");
			list.setList(Arrays.asList(dtoArr));
		} else {
			list.setCount(0);
			list.setStatus("FAIL");
		}

		try {
			XmlMapper mapper = new XmlMapper();
			String xml = mapper.writeValueAsString(list);
			response.setContentType("application/xml; charset=UTF-8");
			response.getWriter().write(xml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// org.json 사용
	private void responseJSON(HttpServletRequest request, HttpServletResponse response) {
		// ListCommand 의 request에 실린 list 받아오기
		WriteDTO[] dtoArr = (WriteDTO[]) request.getAttribute("list");

		JSONObject object = new JSONObject(); // json 오브젝트 생성

		if (dtoArr != null) {
			object.put("Status", "OK");
			object.put("count", dtoArr.length);

			JSONArray arr = new JSONArray(); // json 배열 생성
			for (WriteDTO WriteDTO : dtoArr) {
				JSONObject temp = new JSONObject(); // 배열안에 들어갈 오브젝트 생성
				temp.put("uid", WriteDTO.getUid());
				temp.put("name", WriteDTO.getName());
				temp.put("subject", WriteDTO.getSubject());
				temp.put("content", WriteDTO.getContent());
				temp.put("viewcnt", WriteDTO.getViewCnt());
				temp.put("regdate", WriteDTO.getRegDate());

				arr.put(temp);
			}

			object.put("data", arr);
		} else {
			object.put("Status", "FAIL");
			object.put("count", "0");
		}

		response.setContentType("application/json; charset=UTF-8");
		try {
			response.getWriter().write(object.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// JDOM 라이브러리 사용
	private void responseXML(HttpServletRequest request, HttpServletResponse response) {
		// ListCommand 의 request에 실린 list 받아오기
		WriteDTO[] dtoArr = (WriteDTO[]) request.getAttribute("list");

		// org.jdom2.Document
		Document dom = new Document();
		Element rootElement = new Element("WriteList");
		// <writeList> ... </writeList>

		dom.setRootElement(rootElement);

		Element StatusElement = null;
		Element counterElement = null;

		if (dtoArr != null) {
			StatusElement = new Element("Status").setText("OK");
			counterElement = new Element("Count").setText(dtoArr.length + "");

			// attribute 생성 가능
			counterElement.setAttribute("id", "ccc").setAttribute("pw", "xxx");

			// rootElement 에 등록
			rootElement.addContent(StatusElement);
			rootElement.addContent(counterElement);

			for (WriteDTO writeDTO : dtoArr) {
				Element dataElement = new Element("DATA");

				dataElement.addContent(new Element("uid").setText(writeDTO.getUid() + ""));
				dataElement.addContent(new Element("name").setText(writeDTO.getName()));
				dataElement.addContent(new Element("subject").setText(writeDTO.getSubject()));
				dataElement.addContent(new Element("content").setText(writeDTO.getContent()));
				dataElement.addContent(new Element("viewcnt").setText(writeDTO.getViewCnt() + ""));
				dataElement.addContent(new Element("regdate").setText(writeDTO.getRegDate()));

				rootElement.addContent(dataElement);
			}
		} else {
			StatusElement = new Element("Status").setText("FAIL");
			counterElement = new Element("Count").setText("0");
		}

		// dom 객체를 내보내 주기위한 일종의 stream 같은 객체 (?)
		XMLOutputter outputter = new XMLOutputter();

		response.setContentType("application/xml; charset=UTF-8");
		try {
			// String 형태로 response
			response.getWriter().write(outputter.outputString(dom));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
