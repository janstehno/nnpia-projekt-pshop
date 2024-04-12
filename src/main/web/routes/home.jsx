import Nav from './components/nav.jsx';
import Header from './components/header.jsx';
import Footer from './components/footer.jsx';

function Home() {
  return (
    <div id="container">
      <div id="home">
        <Nav />
        <Header />
        <Footer />
      </div>
    </div>
  );
}

export default Home