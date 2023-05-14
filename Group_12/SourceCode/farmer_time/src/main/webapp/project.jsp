<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page import="java.util.*, java.io.*, information.ProjectInfo" %>
    <%@ page import="java.text.SimpleDateFormat" %>
      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <title>Project</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="./css/style.css" />

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous" />

        <style>
          body {
            min-height: 100vh;
            background: #fafafa;
          }

          .social-link {
            width: 30px;
            height: 30px;
            border: 1px solid #ddd;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #666;
            border-radius: 50%;
            transition: all 0.3s;
            font-size: 0.9rem;
          }

          .social-link:hover,
          .social-link:focus {
            background: #ddd;
            text-decoration: none;
            color: #555;
          }

          .progress {
            height: 10px;
          }

          .card {
            transition: transform 0.3s ease;
            height: 390px;
          }

          .card:hover {
            transform: scale(1.05);
          }

          .fas.fa-plus {
            font-size: 1rem;
          }

          .btn i {
            margin-left: 0.5rem;
          }

          .img-fluid.d-block {
            height: 23vh;
          }

          .card-text-truncate {
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: auto;
          }

          .cursor-pointer {
            cursor: pointer;
          }
        </style>
      </head>

      <body>
        <header>
          <%@ include file="components/header.jsp" %>
            <div class="container-fluid">
              <img src="https://scr.vn/wp-content/uploads/2020/08/H%C3%ACnh-phong-c%E1%BA%A3nh-l%C3%A0ng-qu%C3%AA.jpg"
                class="img-fluid w-100 rounded" alt="Your Image" />
            </div>
        </header>

        <% Date currentDate=new Date(); SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm"); String
          formattedDate=dateFormat.format(currentDate); ArrayList<ProjectInfo> arr = (ArrayList<ProjectInfo>)
            request.getAttribute("arrayListProject");
            %>

            <div class="container py-5">
              <div class="d-flex align-items-center justify-content-between">
                <p class="mb-2 flex-grow-1">
                  <span class="h3 me-2">MY PROJECT</span>
                  <span class="badge bg-danger ml-2" style="vertical-align: top">checklist</span>
                </p>
                <a class="btn btn-danger" href="projectForm.jsp">
                  Add project <i class="fas fa-plus"></i>
                </a>
              </div>
              <p class="text-muted pb-2">Today <%= formattedDate %>
              </p>

              <div class="row pb-5 mb-4">

                <% for(ProjectInfo element : arr) { %>
                  <form id="<%= element.projectId %>" action="showTask" method="post" hidden>
                    <input type="text" value="<%= element.projectId %>" name="projectId" />
                    <button type="submit"></button>
                  </form>
                  <div class="col-lg-3 col-md-6 mb-4 mb-lg-0">
                    <a class="cursor-pointer" onclick="document.getElementById('<%= element.projectId %>').submit();">
                      <div class="card rounded shadow-sm border-0 mb-4">
                        <div class="card-body p-4">
                          <img src="<%= element.img %>" alt="" class="img-fluid d-block mx-auto mb-3" />
                          <h5><span class="text-dark">
                              <%= element.projectName %>
                            </span></h5>
                          <p class="small card-text card-text-truncate text-muted font-italic">
                            <%= element.description %>
                          </p>
                          <div class="d-flex align-items-center justify-content-between mb-3">
                            <% if(element.status.equals("INPROGRESS")) { %>
                              <span class="badge bg-warning ml-2" style="vertical-align: bottom">
                                <%= element.status %>
                              </span>
                              <% } if(element.status.equals("STOPPED")) { %>
                                <span class="badge bg-lightred ml-2" style="vertical-align: bottom">
                                  <%= element.status %>
                                </span>
                                <% } if(element.status.equals("COMPLETED")) { %>
                                  <span class="badge bg-success ml-2" style="vertical-align: bottom">
                                    <%= element.status %>
                                  </span>
                                  <% } %>

                                    <% if(element.priority.equals("HIGH")) { %>
                                      <span class="badge bg-danger ml-2" style="vertical-align: bottom">
                                        <%= element.priority %>
                                      </span>
                                      <% } if(element.priority.equals("MEDIUM")) { %>
                                        <span class="badge bg-sky ml-2" style="vertical-align: bottom">
                                          <%= element.priority %>
                                        </span>
                                        <% } if(element.priority.equals("LOW")) { %>
                                          <span class="badge bg-purple ml-2" style="vertical-align: bottom">
                                            <%= element.priority %>
                                          </span>
                                          <% } %>
                          </div>
                          <span class="text-muted">Finish at: <%= element.finishTime %></span>
                        </div>
                      </div>
                    </a>

                  </div>
                  <% } %>
              </div>
            </div>
            <jsp:include page="components/footer.jsp" />
      </body>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

      </html>