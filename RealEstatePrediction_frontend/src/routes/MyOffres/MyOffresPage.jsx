import MyList from "../../components/MyOffresList/MyList";
import "./MyOffresPage.scss";
import { Link } from "react-router-dom";


function MyOffresPage() {
  return (
    <div className="profilePage">
      <div className="details">
        <div className="wrapper">
          <div className="title">
            <h1>My Offres</h1>
            <Link to="/AddOffre">
              <button>Create New Post</button>
            </Link>
          </div>
          <MyList />
        </div>
      </div>
    </div>
  );
}

export default MyOffresPage;
