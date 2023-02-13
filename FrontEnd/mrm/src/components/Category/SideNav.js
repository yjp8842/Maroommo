import React from 'react';
import {Navigation} from 'react-minimal-side-navigation';
// stylesheet
import 'react-minimal-side-navigation/lib/ReactMinimalSideNavigation.css';

function SideNav() {
    return (
      <>
        <Navigation
            // use your own router's api to get pathname
            activeItemId="/category/react"
            onSelect={({itemId}) => {
              // maybe push to the route
            }}
            items={[
              // {
              //   title: 'Home',
              //   itemId: '/home',
              //   // use your own custom Icon component as well
              //   // elemBefore: () => <Icon name="home" />,
              // },
              {
                title: 'React 공부방',
                itemId: '/react 공부방',
                // elemBefore: () => <Icon name="category" />,
                subNav: [
                  {
                    title: '게시판',
                    itemId: '/react 공부방/게시판',
                  },
                  {
                    title: 'QnA',
                    itemId: '/react 공부방/QnA',
                  },
                ],
              },
              {
                title: 'CS 공부방',
                itemId: '/cs 공부방',
                subNav: [
                  {
                    title: '게시판',
                    itemId: '/cs 공부방/게시판',
                  },
                  {
                    title: 'QnA',
                    itemId: '/cs 공부방/QnA',
                  },
                ],
              },
            ]}
          />
      </>
    );
  }
  export default SideNav;