import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from './Header';
import Navigation from './Navigation';
import Footer from './Footer';

const Layout: React.FC = () => {
  return (
    <div className="sefaz-body">
      <Header />
      <Navigation />
      <main className="sefaz-content">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default Layout;
