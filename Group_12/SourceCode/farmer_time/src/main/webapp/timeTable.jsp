<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <title>Time Table</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="./css/style.css" />
</head>

<body>
  <header>
    <jsp:include page="components/header.jsp" />
  </header>
  <div class="container">
    <div class="table-responsive pt-4">
      <table class="table table-bordered text-center">
        <thead>
          <tr class="bg-info bg-gradient text-white">
            <th class="text-uppercase">Time</th>
            <th class="text-uppercase">Monday</th>
            <th class="text-uppercase">Tuesday</th>
            <th class="text-uppercase">Wednesday</th>
            <th class="text-uppercase">Thursday</th>
            <th class="text-uppercase">Friday</th>
            <th class="text-uppercase">Saturday</th>
            <th class="text-uppercase">Sunday</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="align-middle">09:00 AM</td>
            <td>
              <span
                class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Dance</span>
              <div class="margin-10px-top font-size14">9:00-10:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
            <td>
              <span
                class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Yoga</span>
              <div class="margin-10px-top font-size14">9:00-10:00</div>
              <div class="font-size13 text-light-gray">Marta Healy</div>
            </td>

            <td>
              <span
                class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Music</span>
              <div class="margin-10px-top font-size14">9:00-10:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
            <td>
              <span
                class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Dance</span>
              <div class="margin-10px-top font-size14">9:00-10:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
            <td>
              <span
                class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Art</span>
              <div class="margin-10px-top font-size14">9:00-10:00</div>
              <div class="font-size13 text-light-gray">Kate Alley</div>
            </td>
            <td>
              <span
                class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">English</span>
              <div class="margin-10px-top font-size14">9:00-10:00</div>
              <div class="font-size13 text-light-gray">James Smith</div>
            </td>
          </tr>

          <tr>
            <td class="align-middle">10:00 AM</td>
            <td>
              <span
                class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Music</span>
              <div class="margin-10px-top font-size14">10:00-11:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
            <td class="bg-light-gray"></td>
            <td>
              <span
                class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Art</span>
              <div class="margin-10px-top font-size14">10:00-11:00</div>
              <div class="font-size13 text-light-gray">Kate Alley</div>
            </td>
            <td>
              <span
                class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Yoga</span>
              <div class="margin-10px-top font-size14">10:00-11:00</div>
              <div class="font-size13 text-light-gray">Marta Healy</div>
            </td>
            <td>
              <span
                class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">English</span>
              <div class="margin-10px-top font-size14">10:00-11:00</div>
              <div class="font-size13 text-light-gray">James Smith</div>
            </td>
            <td class="bg-light-gray"></td>
          </tr>

          <tr>
            <td class="align-middle">11:00 AM</td>
            <td>
              <span
                class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Break</span>
              <div class="margin-10px-top font-size14">11:00-12:00</div>
            </td>
            <td>
              <span
                class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Break</span>
              <div class="margin-10px-top font-size14">11:00-12:00</div>
            </td>
            <td>
              <span
                class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Break</span>
              <div class="margin-10px-top font-size14">11:00-12:00</div>
            </td>
            <td>
              <span
                class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Break</span>
              <div class="margin-10px-top font-size14">11:00-12:00</div>
            </td>
            <td>
              <span
                class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Break</span>
              <div class="margin-10px-top font-size14">11:00-12:00</div>
            </td>
            <td>
              <span
                class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Break</span>
              <div class="margin-10px-top font-size14">11:00-12:00</div>
            </td>
          </tr>

          <tr>
            <td class="align-middle">12:00 PM</td>
            <td class="bg-light-gray"></td>
            <td>
              <span
                class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Art</span>
              <div class="margin-10px-top font-size14">12:00-1:00</div>
              <div class="font-size13 text-light-gray">Kate Alley</div>
            </td>
            <td>
              <span
                class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Dance</span>
              <div class="margin-10px-top font-size14">12:00-1:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
            <td>
              <span
                class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Music</span>
              <div class="margin-10px-top font-size14">12:00-1:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
            <td class="bg-light-gray"></td>
            <td>
              <span
                class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Yoga</span>
              <div class="margin-10px-top font-size14">12:00-1:00</div>
              <div class="font-size13 text-light-gray">Marta Healy</div>
            </td>
          </tr>

          <tr>
            <td class="align-middle">01:00 PM</td>
            <td>
              <span
                class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">English</span>
              <div class="margin-10px-top font-size14">1:00-2:00</div>
              <div class="font-size13 text-light-gray">James Smith</div>
            </td>
            <td>
              <span
                class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Music</span>
              <div class="margin-10px-top font-size14">1:00-2:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
            <td class="bg-light-gray"></td>
            <td>
              <span
                class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">English</span>
              <div class="margin-10px-top font-size14">1:00-2:00</div>
              <div class="font-size13 text-light-gray">James Smith</div>
            </td>
            <td>
              <span
                class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Yoga</span>
              <div class="margin-10px-top font-size14">1:00-2:00</div>
              <div class="font-size13 text-light-gray">Marta Healy</div>
            </td>
            <td>
              <span
                class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Music</span>
              <div class="margin-10px-top font-size14">1:00-2:00</div>
              <div class="font-size13 text-light-gray">Ivana Wong</div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <footer>
    <jsp:include page="components/footer.jsp" />
  </footer>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
  integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
  integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</html>