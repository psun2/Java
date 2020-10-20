<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String messageType = null;
if (session.getAttribute("messageType") != null)
	messageType = (String) session.getAttribute("messageType");

String messageContent = null;
if (session.getAttribute("messageContent") != null)
	messageContent = (String) session.getAttribute("messageContent");

if (messageType != null && messageContent != null) {
%>
<div
      class="modal fade"
      id="messageModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header text-white <%if(messageType.equals("오류 메시지")) out.print("bg-danger"); else out.print("bg-primary"); %>">
            <h5 class="modal-title" id="exampleModalLabel"><%=messageType %></h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span class="text-white" aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body"><%=messageContent %></div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">
             	확인
            </button>
          </div>
        </div>
      </div>
    </div>
<%
	session.removeAttribute("messageType");
	session.removeAttribute("messageContent");
}
%>

	<script>
		$('#messageModal').modal('show');
	</script>