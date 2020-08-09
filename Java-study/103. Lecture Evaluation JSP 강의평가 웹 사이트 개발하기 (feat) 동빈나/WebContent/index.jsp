<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>강의평가 웹 사이트</title>
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<!-- 커스텀 css -->
<link rel="stylesheet" href="./css/custom.css" />
<!-- 제이쿼리 -->
<script defer src="./js/jquery-3.5.1.min.js"></script>
<!-- popper -->
<script defer src="./js/popper.min.js"></script>
<!-- 부트스트랩 -->
<script defer src="./js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="index.jsp">강의평가 웹 사이트</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- collapse 이 오타 났을시 dropdown 이 이상한 곳에서 펼쳐지는 현상을 경험함 -->
		<div id="navbar" class="collaps navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="navbar-item active">
					<!-- 현재 index.jsp 에 있어서 active 라는 poroperty 가 들어 갑니다. --> <a
					class="nav-link" href="index.jsp">메인</a>
				</li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="dropdown"
					data-toggle="dropdown"> 회원관리 </a>
					<div class="dropdown-menu" aria-labelledby="dropdown">
						<a class="dropdown-item" href="#"> 로그인 </a> <a
							class="dropdown-item" href="#"> 회원가입 </a> <a
							class="dropdown-item" href="#"> 로그아웃 </a>
					</div></li>
			</ul>

			<!-- form을 이용한 검색창 생성 -->
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="검색 내용을 입력하세요." aria-label="Search">
				<!-- 실제로 검색이 되게 하는 전송 버튼 -->
				<button class="btn btn-outline-success my-2 my-sm-0" type="Submit">검색</button>
			</form>
		</div>
	</nav>
	<!-- 본문 시작 -->
	<section class="container">
		<!-- get 방식으로 사용자가 어떤한 내용을 서버에 전달 할 수 있게 해줌 get 방식은 보안성이 뛰어남 -->
		<!-- form-inline mt-3 : 폼태그 안에서 인라인 방식으로 마진은 3만큼을 준다 -->
		<form method="get" action="index.jsp" class="form-inline mt-3">
			<select name="LectureDivide" class="form-control mx-1 mt-2">
				<option value="전체">전체</option>
				<option value="전공">전공</option>
				<option value="교양">교양</option>
				<option value="기타">기타</option>
			</select>
			<!-- input태그를 이용하여 사용자가 실제로 어떠한 입력을 하여 검색 할 수 있게 함. -->
			<input type="text" name="search" class="form-control mx-1 mt-2"
				placeholder="검색 내용을 입력하세요." />
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>

			<!-- 두가지 버튼을 만들겠습니다. 모달형식으로 만들어 사용자가 강의 평가를 등록 할 수 있게 합니다. -->
			<!-- data-toggle="modal" 은 부트스탭 프레임웍 기능 같음... -->
			<a class="btn btn-primary mx-1 mt-2" data-toggle="modal"
				href="#registerModal">등록하기</a> <a class="btn btn-danger mx-1 mt-2"
				data-toggle="modal" href="#reportModal">신고</a>
		</form>

		<!-- 실제로 사용자가 강의평가를 등록했을시 어떻게 출력이 될지 card 생성 -->
		<div class="card bg-light mt-3">
			<!-- card-header -->
			<div class="card-header bg-light">
				<div class="row">
					<!-- text-left: 왼쪽 정렬 -->
					<!-- 강의에서 나머지 4만큼이라 했으므로, 최대 12가 되는 것 같습니다. -->
					<div class="col-8 text-left">
						컴퓨터개론&nbsp;<small>교수이름</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">A</span>
					</div>
				</div>
			</div>
			<!-- card-body -->
			<div class="card-body">
				<h5 class="card-title">
					<!-- &nbsp; : 띄어쓰기 -->
					정말 좋은 강의에요.&nbsp;<small>(2017년 가을학기)</small>
				</h5>
				<p class="card-text">강의가 많이 널널해서, 솔찍히 많이 배운 건 없는 것 같지만학점도 잘 나오고
					너무 좋은 것 같습니다.</p>
				<div class="row">
					<div class="col-9 text-left">
						성적<span style="color: red;">A</span> 널널<span style="color: red;">A</span>
						강의<span style="color: red;">B</span> <span style="color: green;">(추천:
							15)</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?evaluationID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?evaluationID=">삭제</a>
					</div>
				</div>
			</div>
		</div>

		<!-- 실제로 사용자가 강의평가를 등록했을시 어떻게 출력이 될지 card 생성 -->
		<div class="card bg-light mt-3">
			<!-- card-header -->
			<div class="card-header bg-light">
				<div class="row">
					<!-- text-left: 왼쪽 정렬 -->
					<!-- 강의에서 나머지 4만큼이라 했으므로, 최대 12가 되는 것 같습니다. -->
					<div class="col-8 text-left">
						컴퓨터그래픽스&nbsp;<small>교수이름</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">A</span>
					</div>
				</div>
			</div>
			<!-- card-body -->
			<div class="card-body">
				<h5 class="card-title">
					<!-- &nbsp; : 띄어쓰기 -->
					정말 좋은 강의에요.&nbsp;<small>(2017년 가을학기)</small>
				</h5>
				<p class="card-text">강의가 많이 널널해서, 솔찍히 많이 배운 건 없는 것 같지만학점도 잘 나오고
					너무 좋은 것 같습니다.</p>
				<div class="row">
					<div class="col-9 text-left">
						성적<span style="color: red;">A</span> 널널<span style="color: red;">A</span>
						강의<span style="color: red;">B</span> <span style="color: green;">(추천:
							15)</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?evaluationID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?evaluationID=">삭제</a>
					</div>
				</div>
			</div>
		</div>

		<!-- 실제로 사용자가 강의평가를 등록했을시 어떻게 출력이 될지 card 생성 -->
		<div class="card bg-light mt-3">
			<!-- card-header -->
			<div class="card-header bg-light">
				<div class="row">
					<!-- text-left: 왼쪽 정렬 -->
					<!-- 강의에서 나머지 4만큼이라 했으므로, 최대 12가 되는 것 같습니다. -->
					<div class="col-8 text-left">
						JS&nbsp;<small>교수이름</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">A</span>
					</div>
				</div>
			</div>
			<!-- card-body -->
			<div class="card-body">
				<h5 class="card-title">
					<!-- &nbsp; : 띄어쓰기 -->
					정말 좋은 강의에요.&nbsp;<small>(2017년 가을학기)</small>
				</h5>
				<p class="card-text">강의가 많이 널널해서, 솔찍히 많이 배운 건 없는 것 같지만학점도 잘 나오고
					너무 좋은 것 같습니다.</p>
				<div class="row">
					<div class="col-9 text-left">
						성적<span style="color: red;">A</span> 널널<span style="color: red;">A</span>
						강의<span style="color: red;">B</span> <span style="color: green;">(추천:
							15)</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?evaluationID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?evaluationID=">삭제</a>
					</div>
				</div>
			</div>
		</div>

	</section>



	<!-- Modal 생성 -->
	<!-- modal 같은 경우 header, body, footer 로 나뉩니다. -->
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
		aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<!-- 제목 -->
					<h5 class="modal-title" id="modal">평가등록</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- 내용 -->
					<form action="./evaluationRegisterAction.jsp" method="post">
						<div class="form-row">
							<div class="form-group col-sm-6">
								<label>강의명</label> <input type="text" name="LectureName"
									class="form-control" maxlength="20">
							</div>
							<div class="form-group col-sm-6">
								<label>교수명</label> <input type="text" name="ProfessorName"
									class="form-control" maxlength="20">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-sm-4">
								<label>수강연도</label> <select name="LectureYear"
									class="form-control">
									<option value="2011">2011</option>
									<option value="2012">2012</option>
									<option value="2013">2013</option>
									<option value="2014">2014</option>
									<option value="2015">2015</option>
									<option value="2016">2016</option>
									<option value="2017">2017</option>
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020" selected>2020</option>
									<option value="2021">2021</option>
									<option value="2022">2022</option>
									<option value="2023">2023</option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>수강학기</label> <select name="semesterDivide"
									class="form-control">
									<option value="1학기" selected>1학기</option>
									<option value="여름학기">여름학기</option>
									<option value="2학기">2학기</option>
									<option value="겨울학기">겨울학기</option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>강의 구분</label> <select name="LectureDivide"
									class="form-control">
									<option value="전공" selected>전공</option>
									<option value="교양">교양</option>
									<option value="기타">기타</option>
								</select>
							</div>
						</div>
						<!-- 실제로 사용자가 강의를 평가 할 수 있도록 강의 평가 공간을 만듬 -->
						<div class="form-group">
							<label>제목</label> <input type="text" name="evaluationTime"
								class="form-control" maxlength="30" />
						</div>
						<!-- 내용을 사용자가 담을 수 있도록 하는 공간 -->
						<div class="form-group">
							<label>내용</label>
							<textarea name="evaluationContent" class="form-control"
								maxlength="2048" style="height: 180px;">
						</textarea>
						</div>
						<!-- 하나의 행을 여러개로 나눌때 form-row를 사용합니다. -->
						<div class="form-row">
							<div class="form-group col-sm-3">
								<label>종합</label> <select name="totalScore" class="form-control">
									<option value="A" selected>A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>성적</label> <select name="creditScore"
									class="form-control">
									<option value="A" selected>A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>null null</label> <select name="comfortableScore"
									class="form-control">
									<option value="A" selected>A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>강의</label> <select name="LectureScore"
									class="form-control">
									<option value="A" selected>A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="F">F</option>
								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">취소</button>
							<button type="submit" class="btn btn-primary">등록하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 신고하기 modal -->
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
		aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<!-- 제목 -->
					<h5 class="modal-title" id="modal">신고하기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- 내용 -->
					<form action="./reportAction.jsp" method="post">
						<!-- 실제로 사용자가 강의를 평가 할 수 있도록 강의 평가 공간을 만듬 -->
						<div class="form-group">
							<label>신고 제목</label> <input type="text" name="reportTitle"
								class="form-control" maxlength="30" />
						</div>
						<!-- 내용을 사용자가 담을 수 있도록 하는 공간 -->
						<div class="form-group">
							<label>신고 내용</label>
							<textarea name="reportContent" class="form-control"
								maxlength="2048" style="height: 180px;">
						</textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">취소</button>
							<!-- btn-primary => 파랑 버튼 -->
							<!-- btn-danger => 빨강 버튼 -->
							<button type="submit" class="btn btn-danger">신고하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- footer : 페이지의 젤 마지막 부분 일반적으로 회사정보 등이 담기는 곳 -->
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #ffffff;">
		Copyright &copy; 2020 Sung-Un Park All Rights Reserved. </footer>
</body>
</html>