<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.*, java.io.*, information.Plants" %>
        <%@ page import="java.text.SimpleDateFormat" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />

                <title>Plants</title>

                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
                <link rel="stylesheet" type="text/css" href="./css/style.css" />

                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
                    integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
                    crossorigin="anonymous" />

                <link rel="stylesheet" type="text/css" href="./css/plants.css" />

                <style>
                    #header {
                        background: url('https://static.vecteezy.com/system/resources/previews/006/033/139/original/illustration-of-a-summer-forest-landscape-in-cartoon-style-free-vector.jpg') center center / cover no-repeat;
                        height: 40vh;
                        margin-bottom: 2rem;
                    }

                    #header h3,
                    #header p {
                        position: relative;
                        top: 50%;
                        transform: translateY(-50%);
                    }

                    .img-fluid.d-block {
                        height: 40vh;
                    }

                    .card {
                        transition: transform 0.3s ease;
                        height: 650px;
                    }

                    .card-text-truncate {
                        display: -webkit-box;
                        -webkit-line-clamp: 6;
                        -webkit-box-orient: vertical;
                        overflow-y: auto;
                    }
                </style>
            </head>

            <body>
                <header>
                    <%@ include file="components/header.jsp" %>
                </header>

                <section id="header" class="jumbotron text-center">
                    <h3 class="display-3">PLANTS</h3>
                    <p class="lead">The plant suggestions for farmers.</p>
                </section>

                <section id="gallery">
                    <div class="container">
                        <div class="row">
                            <% ArrayList<Plants> arr = (ArrayList<Plants>) request.getAttribute("arrayListPlants");
                                    for (Plants element : arr) { %>
                                    <div class="col-lg-4 mb-4">
                                        <div class="card">
                                            <img src="<%= element.plantImg %>" alt=""
                                                class="card-img-top img-fluid d-block mx-auto">
                                            <div class="card-body">
                                                <h5 class="card-title">
                                                    <%= element.plantName %>
                                                </h5>
                                                <div class="card-text card-text-truncate mb-3">
                                                    <p class="recipe-desc">Mùa vụ: <%= element.cropName %>
                                                    </p>
                                                    <p class="recipe-desc">Thời điểm gieo trồng: Tháng <%=
                                                            element.plantingMonth %>
                                                    </p>
                                                    <p class="recipe-desc">Thời gian trồng: <%= element.plantingTime %>
                                                            tháng</p>
                                                    <p class="recipe-desc">Điều kiện thời tiết: <%=
                                                            element.weatherConditions %>
                                                    </p>
                                                    <p class="recipe-desc">Một số công dụng: <%= element.uses %>
                                                    </p>
                                                </div>
                                                <a href="" class="btn btn-outline-success btn-sm">Read More</a>
                                                <a href="" class="btn btn-outline-danger btn-sm"><i
                                                        class="far fa-heart"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <% } %>
                        </div>
                    </div>
                </section>



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