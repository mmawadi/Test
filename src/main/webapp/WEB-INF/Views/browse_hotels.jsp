<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expedia Hotels</title>
<link rel="stylesheet" href="resources/css/normalize.css" />
<link rel="stylesheet" href="resources/css/theme.css" />
</head>
<body>
	<div>
		<img src="resources/images/logo.png" alt="logo"
			style="width: 150px; height: 50px" />
	</div>
	<div class="title">Search Hotels</div>

	<div class="container">


		<form action="BrowseHotels" method="post">
			<div class="inputField">
				<ul>
					<li><label for="going-to">Going to</label></li>
					<li><input name="going-to" type="text"
						value="<%=request.getAttribute("going-to")%>"></input></li>
				</ul>

				<ul>

					<li><label for="check-in" class="inputlable">Check-in</label>
						<label for="check-out" style="position: absolute; left: 400px;"
						class="inputlable">Check-out</label></li>
					<li><input id="check-in" name="check-in" type="Date"
						value="<%=request.getAttribute("check-in")%>"
						placeholder="YYYY-MM-DD" min="1-1-2017"></input> <input
						id="check-out" name="check-out" type="Date"
						value="<%=request.getAttribute("check-out")%>"
						placeholder="yyyy-MM-dd" min="1-1-2017"
						style="position: absolute; left: 400px;"></input></li>
				</ul>

				<ul>
					<li><label for="length-stay" class="inputlable">Length
							of Stay</label></li>
					<li><input name="length-stay" type="number" min="1" step="1"
						decimal-precision="0"
						value="<%=request.getAttribute("length-stay")%>"></input></li>
				</ul>
			</div>

			<script type="text/javascript">
				var today = new Date();

				var dd = today.getDate();
				var mm = today.getMonth() + 1; //January is 0!
				var yyyy = today.getFullYear();
				if (dd < 10) {
					dd = '0' + dd
				}
				if (mm < 10) {
					mm = '0' + mm
				}

				today = yyyy + '-' + mm + '-' + dd;

				var todayelemnt = document.getElementById("check-in");
				todayelemnt.setAttribute("min", today);

				var elemnt = document.getElementById("check-out");
				elemnt.setAttribute("min", today);

				var tomorrow = new Date();
				tomorrow.setDate(tomorrow.getDate() + 1);

				var tomorrowdd = tomorrow.getDate();
				var tomorrowmm = tomorrow.getMonth() + 1; //January is 0!
				var tomorrowyyyy = tomorrow.getFullYear();
				if (tomorrowdd < 10) {
					tomorrowdd = '0' + tomorrowdd
				}
				if (tomorrow < 10) {
					tomorrow = '0' + tomorrow
				}

				tomorrow = tomorrowyyyy + '-' + tomorrowmm + '-' + tomorrowdd;

				var elemnt = document.getElementById("check-out");
				elemnt.setAttribute("min", tomorrow);
			</script>


			<div class="inputField">
				<ul>
					<li><label for="min-star-rate" class="inputlable">Min
							Star Rate</label> <label for="max-star-rate" class="inputlable"
						style="position: absolute; left: 400px;">Max Star Rate</label></li>
					<li><input name="min-star-rate" type="number" step="0.1"
						value="<%=request.getAttribute("min-star-rate")%>"></input> <input
						name="max-star-rate" step="0.1"
						value="<%=request.getAttribute("max-star-rate")%>" type="number"
						style="position: absolute; left: 400px;"></input></li>
				</ul>
			</div>

			<div class="inputField">
				<ul>
					<li><label for="min-total-rate" class="inputlable">Min
							Total Rate</label> <label for="max-total-rate" class="inputlable"
						style="position: absolute; left: 400px;">Max Total Rate</label></li>
					<li><input name="min-total-rate" type="number"
						value="<%=request.getAttribute("min-total-rate")%>"></input> <input
						name="max-total-rate" type="number"
						value="<%=request.getAttribute("max-total-rate")%>"
						style="position: absolute; left: 400px;"></input></li>
				</ul>
			</div>

			<div class="inputField">
				<ul>
					<li><label for="min-guest-rate" class="inputlable">Min
							Guest Rate</label> <label for="max-total-rate" class="inputlable"
						style="position: absolute; left: 400px;">Max Guest Rate</label></li>
					<li><input name="min-guest-rate" type="number" step="0.1"
						value="<%=request.getAttribute("min-guest-rate")%>"></input> <input
						name="max-guest-rate" type="number" step="0.1"
						value="<%=request.getAttribute("max-guest-rate")%>"
						style="position: absolute; left: 400px;"></input></li>
				</ul>
			</div>


			<div class="inputField" id="submitField"
				style="position: relative; top: 40px; left: 40px;">
				<input id="submitBtn" type="submit" value="Search"></input>
			</div>

		</form>
	</div>

	<div>
		<%
			ServletContext sc = this.getServletContext();
			@SuppressWarnings("unchecked")
			java.util.List<com.expedia.models.HotelOfferInfo> offersList = (java.util.List<com.expedia.models.HotelOfferInfo>) sc
					.getAttribute("hotelOffers");

			if (offersList != null && offersList.size() > 0) {
		%>
		<table class="styledTable">

			<thead>
				<th></th>
				<th>Hotel Name</th>
				<th>City</th>
				<th>No. of Available Rooms</th>
				<th>Avg rate per night</th>
				<th>Rating</th>
				<th>Total Reviews</th>
			</thead>
			<%
				for (Integer i = 0; i < offersList.size(); i++) {
			%>

			<tr>

				<td><img src="<%=offersList.get(i).getHotelImageUrl()%>"
					width="120" /></td>
				<td><%=offersList.get(i).getHotelName()%></td>
				<td><%=offersList.get(i).getCity()%></td>
				<td><%=offersList.get(i).getNumberOfRoomsLeft()%></td>
				<td><%=offersList.get(i).getAveragePriceValue()%></td>
				<td><%=offersList.get(i).getHotelStarRating()%></td>
				<td><%=offersList.get(i).getHotelReviewTotal()%></td>

			</tr>

			<%
				}
			%>
		</table>
		<%
			} else {
				System.out.println("Invalid list");
		%>
		<div>
			<label class="error">No hotels available</label>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>