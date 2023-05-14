<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Sign up</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link href="css/style.css" type="text/css" rel="stylesheet" />

    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="app d-flex flex-column vh-100 justify-content-between">
      <div class="container-fluid">
        <section
          class="align-items-center padding"
          style="background-color: #eee"
        >
          <div
            class="row d-flex justify-content-center align-items-center h-100"
          >
            <div class="col-lg-12 col-xl-11">
              <div class="card" style="border-radius: 25px">
                <div class="card-body p-md-5">
                  <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                      <p
                        class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"
                        style="color: black"
                      >
                        Sign up
                      </p>

                      <form
                        action="/login/signup"
                        method="POST"
                        class="mx-1 mx-md-4"
                      >
                        <div class="d-flex flex-row align-items-center mb-4">
                          <div class="form-outline flex-fill mb-0">
                            <label class="form-label" for="form3Example1c"
                              >Your Name</label
                            >
                            <input
                              type="text"
                              id="form3Example1c"
                              class="form-control"
                              name="name"
                            />
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <div class="form-outline flex-fill mb-0">
                            <label class="form-label" for="form3Example3c"
                              >Your Email</label
                            >
                            <input
                              type="email"
                              id="form3Example3c"
                              class="form-control"
                              name="email"
                            />
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <div class="form-outline flex-fill mb-0">
                            <label class="form-label" for="form3Example4c"
                              >Password</label
                            >
                            <input
                              type="password"
                              id="form3Example4c"
                              class="form-control"
                              name="password"
                            />
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <div class="form-outline flex-fill mb-0">
                            <label class="form-label" for="form3Example4cd"
                              >Confirm your password</label
                            >
                            <input
                              type="password"
                              id="form3Example4cd"
                              class="form-control"
                              name="repeat"
                            />
                          </div>
                        </div>

                        <div
                          class="form-check d-flex justify-content-center mb-5"
                        >
                          <label class="form-check-label fw-bold">
                            Have already an account?
                            <a href="login.jsp">
                              <u>Login</u>
                            </a>
                          </label>
                        </div>

                        <div
                          class="d-flex justify-content-center mx-4 mb-3 mb-lg-4"
                        >
                          <button type="submit" class="btn btn-primary btn-lg">
                            Register
                          </button>
                        </div>
                      </form>
                    </div>
                    <div
                      class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2"
                    >
                      <img
                        src="https://images.careerbuilder.vn/content/images/linh-moi-de-hoa-hop-voi-moi-truong-moi-careerbuilder.png"
                        class="img-fluid"
                        alt="Sample image"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <jsp:include page="components/footer.jsp" />
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
      integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
      integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
