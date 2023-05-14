<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Login</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />

    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="./css/style.css" />
  </head>
  <body>
    <div class="app d-flex flex-column vh-100 justify-content-between">
      <div class="container" style="padding-top: 10%">
        <section class="vh-100 align-items-center">
          <div class="container-fluid h-custom">
            <div
              class="row d-flex justify-content-center align-items-center h-100"
            >
              <div class="col-md-9 col-lg-6 col-xl-5">
                <img
                  src="https://nghenghiep.vieclam24h.vn/wp-content/uploads/2022/06/da3-768x432.jpg"
                  class="img-fluid"
                  alt="Sample image"
                />
              </div>
              <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <form action="/login" method="POST">
                  <!-- Email input -->
                  <div class="form-outline mb-4">
                    <label class="form-label" for="typeEmail"
                      >Email address</label
                    >
                    <input
                      type="email"
                      id="typeEmail"
                      class="form-control"
                      name="email"
                    />
                  </div>

                  <!-- Password input -->
                  <div class="form-outline mb-3">
                    <label class="form-label" for="typePassword"
                      >Password</label
                    >
                    <input
                      type="password"
                      id="typePassword"
                      class="form-control"
                      name="password"
                    />
                  </div>

                  <div
                    class="d-flex justify-content-between align-items-center"
                  >
                    <!-- Checkbox -->
                    <div class="form-check mb-0">
                      <input
                        class="form-check-input me-2"
                        type="checkbox"
                        id="form2Example3"
                        name="remember"
                      />
                      <label class="form-check-label" for="form2Example3">
                        Remember me
                      </label>
                    </div>
                    <a href="#!" class="text-body">Forgot password?</a>
                  </div>

                  <div class="text-center text-lg-start mt-4 pt-2">
                    <button
                      type="submit"
                      class="btn btn-primary btn-lg"
                      style="padding-left: 2.5rem; padding-right: 2.5rem"
                    >
                      Login
                    </button>
                    <p class="small fw-bold mt-2 pt-1 mb-0">
                      Don't have an account?
                      <a href="/farmer_time/signup.jsp" class="link-danger">Register</a>
                    </p>
                  </div>
                </form>
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
