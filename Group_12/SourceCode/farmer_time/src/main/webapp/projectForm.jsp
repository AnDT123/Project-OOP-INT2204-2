<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Project</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
        integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous" />

    <link rel="stylesheet" href="./css/style.css" />
</head>

<body>
    <header>
        <jsp:include page="components/header.jsp" />
    </header>

    <section class="vh-100">
        <div class="container py-5">
            <div class="row d-flex justify-content-center align-items-center">
                <div class="col-md-8 mb-4">
                    <div class="card mb-4">
                        <div class="card-header py-3 bg-danger text-white">
                            <h5 class="mb-0">ADD PROJECT</h5>
                        </div>
                        <form action="addProject" method="post" class="card-body">
                            <div class="mb-3">
                                <label class="form-label">Project Name</label>
                                <input type="text" onkeypress="return event.keyCode != 13;"
                                    class="form-control" name="projectName" />
                                <div class="invalid-feedback">
                                    Please provide a title project.
                                </div>
                            </div>


                            <div class="mb-3">
                                <label class="form-label">Descriptions</label>
                                <textarea type="text" class="form-control" name="des" style="height: 80px;"></textarea>
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Image</label>
                                <textarea type="text" class="form-control" name="img"></textarea>
                            </div>

                            <div class="row mb-4">
                                <div class="col">
                                    <div class="mb-3">
                                        <label class="form-label">Priority</label>
                                        <select name="priority" class="form-control border" id="gender-input">
                                            <option value="">--Select priority--</option>
                                            <option value="LOW">LOW</option>
                                            <option value="MEDIUM">MEDIUM</option>
                                            <option value="HIGH">HIGH</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="mb-3">
                                        <label class="form-label">Status</label>
                                        <select name="status" class="form-control border" id="gender-input">
                                            <option value="">--Select status--</option>
                                            <option value="INPROGRESS">INPROGRESS</option>
                                            <option value="STOPPED">STOPPED</option>
                                            <option value="COMPLETED">COMPLETED</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row mb-4">
                                <div class="col">
                                    <div class="form-outline">
                                        <label class="form-label" for="form6Example1">Start Time</label>
                                        <input type="datetime-local" name="startTime" id="form6Example1"
                                            class="form-control" />
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-outline">
                                        <label class="form-label" for="form6Example2">Finish Time</label>
                                        <input type="datetime-local" name="finishTime" id="form6Example2"
                                            class="form-control" />
                                    </div>
                                </div>
                            </div>

                            <div class="d-block p-3 text-right card-footer">
                                <button class="btn btn-danger btn-sm" type="submit">Add</button>
                                <a class="mr-2 btn btn-primary btn-sm" href="showProject">Cancel</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </section>
</body>

</html>