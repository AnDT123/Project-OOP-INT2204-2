<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="showProject"><i class="fas fa-tree mr-2"></i>&emsp;TIME</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="showProject">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="showTask">Tasks</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="timeTable.jsp">Time Table</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="plants">Plants</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="getData">Weather</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="getCalendar"> Calendar </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login.jsp" onclick="confirmLogout(event)" tabindex="-1" aria-disabled="true">Log
            out</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
<script>
  function confirmLogout(event) {
    // Hiển thị hộp thoại xác nhận
    var confirmation = confirm("Are you sure want to log out?");

    if (!confirmation) {
      event.preventDefault(); // Ngăn chặn hành động mặc định của form
    }
  }
</script>