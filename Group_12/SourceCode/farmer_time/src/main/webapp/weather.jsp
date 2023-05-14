<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="java.util.Date" %> <%@ page
import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Weather Data App</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
      integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <header>
      <jsp:include page="components/header.jsp" />
    </header>
    <% Date currentDate = new Date(); SimpleDateFormat dateFormat = new
    SimpleDateFormat("dd/MM/yyyy HH:mm"); String formattedDate =
    dateFormat.format(currentDate); %>

    <div class="container">
      <div class="row">
        <div
          class="col-md-6 pt-5 mx-auto row d-flex justify-content-center align-items-center"
        >
          <h3 class="mb-4 pb-2 fw-normal">Welcome to the Weather forecast!</h3>
          <form
            action="getData"
            method="post"
            class="input-group rounded mb-3"
          >
            <input
              type="text"
              name="city"
              class="form-control rounded pr-2"
              placeholder="The city"
            />
            <button type="submit" class="btn btn-danger pl-2">Check!</button>
          </form>
        </div>
      </div>
    </div>
    <section class="vh-100">
      <div class="container py-5 h-75">
        <div class="row d-flex justify-content-center align-items-center h-75">
          <div class="col-md-8 col-lg-6 col-xl-4">
            <div class="card" style="color: #4b515d; border-radius: 35px">
              <div class="card-body p-4">
                <div class="d-flex">
                  <h6 class="flex-grow-1">${weather.city}</h6>
                  <h6><%= formattedDate %></h6>
                </div>

                <div class="d-flex flex-column text-center mt-5 mb-4">
                  <h6
                    class="display-4 mb-0 font-weight-bold"
                    style="color: #1c2331"
                  >
                    ${weather.temperature}°C
                  </h6>
                  <span class="small" style="color: #868b94"
                    >${weather.description}</span
                  >
                </div>

                <div class="d-flex align-items-center">
                  <div class="flex-grow-1" style="font-size: 1rem">
                    <div>
                      <i class="fas fa-wind fa-fw" style="color: #868b94"></i>
                      <span class="ms-1"> ${weather.windSpeed} km/h </span>
                    </div>
                    <div>
                      <i class="fas fa-tint fa-fw" style="color: #868b94"></i>
                      <span class="ms-1"> ${weather.humidity}% </span>
                    </div>
                    <div>
                      <i class="fas fa-sun fa-fw" style="color: #868b94"></i>
                      <span class="ms-1"> ${weather.fellsLike}°C </span>
                    </div>
                  </div>
                  <div>
                    <img
                      src="${weather.iconImage}"
                      width="120px"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </body>
</html>
